package com.company.Model;

import java.util.*;
import java.lang.*;

public class Business {

    public List<Map.Entry<List<String>,Integer>> createNetwork(List<Activity> inputList) {

        Map<String,Integer> weight = new HashMap<String,Integer>();
        for (Activity activity : inputList) {
            weight.put(activity.getActivity(),activity.getDuration());
        }

        String parentNode = inputList.get(0).getActivity();

        int c = 1;
        List<List<String>> lstNetwork= new ArrayList<List<String>>();
        List<List<String>> clonelstNetwork= new ArrayList<List<String>>();
        for (Activity activity : inputList) {
            List<String> tmpList= new ArrayList<String>();
            String node= activity.getActivity();
            /* Get the Parent node*/
            if(node.equals(parentNode) && c == 1) {
                tmpList.add(node);
                clonelstNetwork.add(tmpList);
                c++;
            } else {
                /* If the node is not a parent node*/
                String str=activity.getDependency();
                String[] strArray=str.split(",");
                for(int i = 0; i < strArray.length; i++) {
                    String depend=strArray[i];
                    if(parentNode.equals(depend)) {
                        for (List<String> lst : lstNetwork) {
                            /* Find the parent node and append to it*/
                            if(lst.size() == 1 && lst.get(0).equals(depend)) {
                                tmpList= new ArrayList<String>();
                                int index=lstNetwork.indexOf(lst);
                                tmpList.add(lst.get(0));
                                tmpList.add(node);
                                clonelstNetwork.set(index, tmpList);
                            }else {
                                tmpList= new ArrayList<String>();
                                tmpList.add(depend);
                                tmpList.add(node);
                                clonelstNetwork.add(tmpList);
                            }
                        }
                    }else {
                        /* Already there are some network in the list*/
                        for (int j = 0; j < lstNetwork.size(); j++) {
                            List<String> createdList=lstNetwork.get(j);
                            for (int k = 0; k < createdList.size(); k++) {
                                String nod=createdList.get(k);
                                if(depend.equals(nod)) {
                                    tmpList= new ArrayList<String>();
                                    tmpList.addAll(createdList);
                                    tmpList.add(k+1,node);
                                    clonelstNetwork.set(j,tmpList);
                                    }
                                }
                            }
                        }
                    }
                }

            lstNetwork=new ArrayList<List<String>>(clonelstNetwork);
        }

        //Validation Two: Check for lstNetwork, starting node and end node must not be equal, otherwise cyclic
        for (List<String> list : lstNetwork) {
            if(list.size()>1) {
                if(list.get(0).equals(list.get(list.size()-1))) {
                    System.out.println("Network must not be cyclic");
                    return null;
                }
            }
        }

        Map<List<String>,Integer> finalOp= new HashMap<List<String>,Integer>();
        for (List<String> list : lstNetwork) {
            int sum=0;
            for(String str: list) {
                sum=sum+weight.get(str);
            }

            finalOp.put(list, sum);
        }

        List<Map.Entry<List<String>,Integer>> sortedFinal = new ArrayList<Map.Entry<List<String>, Integer>>(finalOp.entrySet());

        Collections.sort(sortedFinal, new Comparator<Map.Entry<List<String>,Integer>>() {
            public int compare(Map.Entry<List<String>,Integer> o1, Map.Entry<List<String>,Integer> o2) {
                return ( o2.getValue()).compareTo(o1.getValue());
            }
        });

        System.out.println("ResultONe "+sortedFinal);
        return sortedFinal;

    }
}

