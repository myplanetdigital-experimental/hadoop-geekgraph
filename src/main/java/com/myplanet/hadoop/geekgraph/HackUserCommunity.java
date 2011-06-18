package com.myplanet.hadoop.geekgraph;

import java.io.IOException;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



/**
 * This MapReduce job will count the total number of Bixi records in the data dump.
 *
 */
public class HackUserCommunity extends Configured implements Tool {

	public enum Count {
		TOTAL_RECORDS,
		UNIQUE_KEYS,
		NULL_RECORDS
	}

	/**
	 * Class for map function input user / follows int pair to a user group
	 *
	 */ 
	public static class UserGroupCounterMapper extends Mapper<LongWritable, Text, LongWritable, Text> {

		@Override
		protected void map(LongWritable lineNumber, Text value, Context context) throws IOException,
				InterruptedException {
			String stringValue = value.toString();
			String[] keyValuePair = stringValue.split("\t");
			LongWritable userid = new LongWritable(Long.parseLong(keyValuePair[1]));
			context.getCounter(Count.TOTAL_RECORDS).increment(1);
			String userGroupString = CommunityMapSingleton.findCommunity(Long.parseLong(keyValuePair[0]));
			
			if (userGroupString == null) {
				context.getCounter(Count.NULL_RECORDS).increment(1);				
			} else {
				Text userGroup = new Text(userGroupString);
				context.write(userid, userGroup);
			}
		}

	}

	public void configureJob(Job job) {
		job.setInputFormatClass(TextInputFormat.class);
	}

	public static void main(String[] args) throws Exception {
		int result = ToolRunner.run(new Configuration(), new HackUserCommunity(), args);
		System.exit(result);
	}

	public enum RecordCounterCount {
		UNIQUE_KEYS
	}

	public static class RecordCounterReducer extends Reducer<LongWritable, Text, Text, NullWritable> {

		@Override
		protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			context.getCounter(RecordCounterCount.UNIQUE_KEYS).increment(1);

			SortedSet<String> set = new TreeSet<String>();
			for (Text value : values) {
				set.add(value.toString());
			}
			String out = "";
			Iterator<String> i = set.iterator();
			while (i.hasNext()) {
				out += ":" + i.next();
			}
			context.write(new Text(out), null);
		}

	}

	public int run(String[] args) throws Exception {
		Configuration conf = getConf();

        if (args.length != 2) {
        	System.err.println("Usage: " + getClass().getName() + " <input> <oTextutput>");
        	System.exit(2);
        }

        // Creating the MapReduce job (configuration) object
        Job job = new Job(conf);
        job.setJarByClass(getClass());
        job.setJobName(getClass().getName());

        // Tell the job which Mapper and Reducer to use (classes defined above)
        job.setMapperClass(UserGroupCounterMapper.class);
		job.setReducerClass(RecordCounterReducer.class);

        configureJob(job);

		// This is what the Mapper will be outputting to the Reducer
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);

		// This is what the Reducer will be outputting
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		// Setting the input folder of the job 
		FileInputFormat.addInputPath(job, new Path(args[0]));

		// Preparing the output folder by first deleting it if it exists
        Path output = new Path(args[1]);
        FileSystem.get(conf).delete(output, true);
	    FileOutputFormat.setOutputPath(job, output);

		return job.waitForCompletion(true) ? 0 : 1;
	}	
}
