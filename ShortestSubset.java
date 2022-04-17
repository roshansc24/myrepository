package com.algo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ShortestSubset {

	public static void main(String[] args) {
		int [] array = {1,11,100,1,0,200,80,3,2,1,250,20,10};
		//1,11,100,1,0,200
		//200,3,2,1,250
		int input = 280;
		
		
		Map<String, List<Integer>> map = devideArrays(array,input);
		Map<Integer, List<Integer>> resultMap = new HashMap<Integer, List<Integer>>();
		List<Integer> finalList = new ArrayList<Integer>();
		for (Map.Entry<String, List<Integer>> set : map.entrySet()) {
           List<Integer> list = (List<Integer>)set.getValue();
           int total = 0;
           List<Integer> mapValue_List = new ArrayList<Integer>();
           for(int index = list.size()-1 ; 0 <= index; index--) {
        	    total = total + list.get(index);
        	    mapValue_List.add(list.get(index));
        	    if(total >= input) {
        	    	break;
        	    }
           }
           resultMap.put(total, mapValue_List);
           finalList.add(total);
       }
	   
		List<Integer> outputList = resultMap.get(getSmallestValue(finalList,input));
		outputList.forEach(result -> System.out.print(result + " "));
	}
	
	public static HashMap devideArrays(int [] mainArray,int input) {
		int total = 0;
		HashMap map = new HashMap<String, List<Integer>>();
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		int mapIndex = 1;
		int length = 0;
		
		for(int index=0;index<= mainArray.length;index++) {
			total = total + mainArray[index];
			tempArray.add(mainArray[index]);
			if(total >= input) {
				map.put(mapIndex, tempArray);
				mapIndex++;
				tempArray = new ArrayList<Integer>();
				index--;
				length++;
				total = 0;
				
				
			}
			if(mainArray.length == index+length) {
				try {
					tempArray.add(mainArray[mainArray.length-1]);
					map.put(mapIndex, tempArray);
					break;
				}catch(Exception e) {
					break;
				}
			}
		}
		return map;
	}
	
	public static int getSmallestValue(List<Integer> list,int input) {
		int c = list.stream()
	            .min(Comparator.comparingInt(i -> Math.abs(i - input)))
	            .orElseThrow(() -> new NoSuchElementException("No value present"));
		
		return c;
	}
	
}
