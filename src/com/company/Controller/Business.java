package com.company.Controller;

import com.company.Model.Activity;

import java.util.*;
import java.lang.*;
import java.util.Map.Entry;

public class Business {

    public List<Map.Entry<List<String>,Integer>> createNetwork(List<Activity> inputList) {

        Map<String,Integer> weight = new HashMap<String,Integer>();
        for (Activity activity : inputList) {
            weight.put(activity.getActivity(),activity.getDuration());
        }

        List<String> parentNodes= new ArrayList<String>();
        for (Activity activity : inputList) {
            if(activity.getDependency().equals("root")) {
                parentNodes.add(activity.getActivity());
            }

        }

        //Validation: There must be one root node
        if(parentNodes.size()>1) {
            System.out.println("There must be one parent node");
            return null;
        }

        //Prepare list of networks
        List<String> root=new ArrayList<String>();
        root.add("root");
        List<List<String>> network= new ArrayList<List<String>>();
        Map<String,List<String>> mapInput= new LinkedHashMap<String,List<String>>();
        List<List<String>> cloneNetwork= new ArrayList<List<String>>();
        for (Activity activity : inputList) {
            String key= activity.getActivity();
            String[] value= activity.getDependency().split(",");
            List<String> dependencyList= new ArrayList<String>();
            for (String string : value) {
                dependencyList.add(string);
            }
            mapInput.put(key, dependencyList);
        }

        //Prepare a list from map entry set to access key and value both.
        List<Map.Entry<String, List<String>>> list = new LinkedList<Map.Entry<String, List<String>>>(mapInput.entrySet());

        //Separate all root from input list and add them to network.
        for (Entry<String, List<String>> entry : list) {
            if(entry.getValue().equals(root)){
                List<String> lst= new ArrayList<String>();
                lst.add(entry.getKey());
                cloneNetwork.add(lst);
                mapInput.remove(entry.getKey());
            }

        }
        list = new LinkedList<Map.Entry<String, List<String>>>(mapInput.entrySet());
        network= new ArrayList<List<String>>(cloneNetwork);

        //Executing a while loop to create the network further as per  size of input list.
        int bound=list.size();
        int loopCount=0;
        while(loopCount<bound) {
            cloneNetwork= new ArrayList<List<String>>();
            cloneNetwork=this.insertNodes(list, network);
            network= new ArrayList<List<String>>(cloneNetwork);
            loopCount++;
        }

        //Validation: Check for lstNetwork, starting node and end node must not be equal, otherwise cyclic
        for (List<String> list1 : network) {
            for(String str: list1) {
                if(Collections.frequency(list1,str)>1){
                    return null;
                }
            }
        }

        Map<List<String>,Integer> finalOp= new HashMap<List<String>,Integer>();
        for (List<String> list1 : network) {
            int sum=0;
            for(String str: list1) {
                sum=sum+weight.get(str);
            }

            finalOp.put(list1, sum);
        }

        List<Map.Entry<List<String>,Integer>> sortedFinal = new ArrayList<Map.Entry<List<String>, Integer>>(finalOp.entrySet());

        Collections.sort(sortedFinal, new Comparator<Map.Entry<List<String>,Integer>>() {
            public int compare(Map.Entry<List<String>,Integer> o1, Map.Entry<List<String>,Integer> o2) {
                return ( o2.getValue()).compareTo(o1.getValue());
            }
        });

        System.out.println("Sorted with weigtage "+sortedFinal);
        return sortedFinal;
    }
    private List<List<String>> insertNodes(List<Entry<String, List<String>>> list, List<List<String>> network) {
        List<List<String>> cloneNetwork= new ArrayList<List<String>>(network);
        int count=0;
        for(int i=0;i<network.size();i++) {
            count=0;
            List<String> innerList=network.get(i);
            String parentNode=innerList.get(innerList.size()-1);
            for(int j=0;j<list.size();j++) {
                Map.Entry<String, List<String>> m1=(Map.Entry<String, List<String>>) list.get(j);
                String key=m1.getKey();
                List<String> dependency= m1.getValue();
                if(dependency.contains(parentNode)) {
                    if(count==0) {
                        List<String> tempList=new ArrayList<String>();
                        tempList.addAll(innerList);
                        tempList.add(key);
                        cloneNetwork.set(i,tempList);
                        count++;
                    }else {
                        List<String> tempList=new ArrayList<String>();
                        tempList.addAll(innerList);
                        tempList.add(key);
                        cloneNetwork.add(tempList);

                    }

                }
            }
        }
        return cloneNetwork;

    }
}

