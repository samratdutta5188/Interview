package com.algorithms;

import java.util.HashSet;
import java.util.Iterator;

public class JobScheduling {

	private static int[] startTime, endTime;
	private static HashSet<Integer> clashingIndexSet, committedIntervalIndex;

	private static void processInput(String input) {
		clashingIndexSet = new HashSet<Integer>();
		committedIntervalIndex = new HashSet<Integer>();
		String str = input.replace("{", "").replace("}", "");
		String[] jobs = str.split(",");
		startTime = new int[jobs.length];
		endTime = new int[jobs.length];
		for (int i = 0; i < jobs.length; i++) {
			String[] temp = jobs[i].split("#");
			if (temp[0].contains("PM")) {
				startTime[i] = (12 + Integer
						.parseInt(temp[0].replace("PM", ""))) % 24;
			} else if (temp[0].contains("AM")
					&& Integer.parseInt(temp[0].replace("AM", "")) != 12) {
				startTime[i] = Integer.parseInt(temp[0].replace("AM", ""));
			} else {
				startTime[i] = Integer.parseInt(temp[0].replace("AM", "")
						.replace("PM", "")) - 12;
			}
			if (temp[1].contains("PM")
					&& Integer.parseInt(temp[1].replace("PM", "")) != 12) {
				endTime[i] = (12 + Integer.parseInt(temp[1].replace("PM", ""))) % 24;
			} else if (temp[1].contains("AM")) {
				endTime[i] = Integer.parseInt(temp[1].replace("AM", ""));
			} else {
				endTime[i] = Integer.parseInt(temp[1].replace("AM", "")
						.replace("PM", ""));
			}
		}
	}

	private static int findEarliest(int[] array) {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if ((!clashingIndexSet.contains(i))
					&& (!committedIntervalIndex.contains(i))) {
				if (array[i] < min) {
					min = array[i];
					index = i;
				}
			}
		}
		return index;
	}

	/*
	 * Will return the indexes that are committed and that clashes as well. The
	 * if condition checks if there is any other interval being clashed by this
	 * committed interval to add the clashing interval's indexes to the
	 * clashingIndexSet. For example, if the committed interval is {11AM#1PM}
	 * then the intervals that are clashed are {7AM#3PM} and {10AM#12PM} whose
	 * indexes 2,5 are added along with the committed interval's index 1 to the
	 * clashingIndexSet. So clashingIndexSet - [1, 2, 5]
	 */

	private static void clashingIntervals() {
		Iterator committedIntervalIndexIterator = committedIntervalIndex
				.iterator();
		while (committedIntervalIndexIterator.hasNext()) {
			int committedInterval = (int) committedIntervalIndexIterator.next();
			int committedStartTime = startTime[committedInterval], committedEndTime = endTime[committedInterval];
			for (int i = 0; i < startTime.length; i++) {
				if ((startTime[i] < committedEndTime && startTime[i] >= committedStartTime)
						|| (endTime[i] <= committedEndTime && endTime[i] > committedStartTime)
						|| (endTime[i] < committedStartTime && startTime[i] >= committedStartTime)
						|| (startTime[i] < committedEndTime && endTime[i] >= committedEndTime)) {
					clashingIndexSet.add(i);
				}
			}
		}
		clashingIndexSet.removeAll(committedIntervalIndex);
	}

	public static int execute(String input) {
		JobScheduling.processInput(input);
		while ((JobScheduling.committedIntervalIndex.size() + JobScheduling.clashingIndexSet
				.size()) < JobScheduling.startTime.length) {
			int earliestEndTimeIndex = JobScheduling
					.findEarliest(JobScheduling.endTime);
			JobScheduling.committedIntervalIndex.add(earliestEndTimeIndex);
			JobScheduling.clashingIntervals();
		}
		return committedIntervalIndex.size() * 500;
	}

	private static String processOutput(String input) {
		StringBuffer output = new StringBuffer("{");
		String[] inputSplit = input.replace("{", "").replace("}", "")
				.split(",");
		for (Integer i : committedIntervalIndex) {
			output.append(inputSplit[i]).append(",");
		}
		output.deleteCharAt(output.length() - 1).append("}");
		return output.toString();
	}

	public static void main(String args[]) {
		String input = "{6AM#8AM,11AM#1PM,7AM#3PM,7AM#10AM,10AM#12PM,2PM#4PM,1PM#4PM,8AM#9AM}";
		System.out.println("Max profit----- " + execute(input));
		System.out.println("Output-----" + processOutput(input));
	}

}
