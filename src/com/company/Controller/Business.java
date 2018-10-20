package com.company.Controller;

import com.company.Model.Activity;

import java.util.*;
import java.lang.*;

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
        int c = 1;
        List<List<String>> lstNetwork= new ArrayList<List<String>>();
        List<List<String>> clonelstNetwork= new ArrayList<List<String>>();
        for (Activity activity : inputList) {
            List<String> tmpList = new ArrayList<String>();
            String node= activity.getActivity();
            //Get the parent node
            if(parentNodes.contains(node) && c == 1) {
                tmpList= new ArrayList<String>();
                tmpList.add(node);
                clonelstNetwork.add(tmpList);
                c++;
                //break;
            }else {
                // If incoming node is not a parent node.
                String str=activity.getDependency();
                String[] strArray=str.split(",");
                for(int i=0;i<strArray.length;i++) {
                    String depend=strArray[i];
                    if(parentNodes.contains(depend)) {
                        // If incoming node is direct child of parent node.
                        if(!(lstNetwork.size()>0)) {
                            // If parent list is empty.
                            tmpList= new ArrayList<String>();
                            tmpList.add(depend);
                            tmpList.add(node);
                            clonelstNetwork.add(tmpList);
                        }else {
                            for (List<String> lst : lstNetwork) {
                                if(lst.size()==1&&lst.get(0).equals(depend)) {
                                    // Incoming node is appended to its parent node.
                                    tmpList= new ArrayList<String>();
                                    int index=lstNetwork.indexOf(lst);
                                    tmpList.add(lst.get(0));
                                    tmpList.add(node);
                                    clonelstNetwork.set(index, tmpList);
                                }else {
                                    // If there more than 1 path.
                                    tmpList= new ArrayList<String>();
                                    tmpList.add(depend);
                                    tmpList.add(node);
                                    clonelstNetwork.add(tmpList);
                                }
                            }
                        }



                    }else {

                        if(lstNetwork.size()<1) {
                            tmpList= new ArrayList<String>();
                            tmpList.add(depend);
                            tmpList.add(node);
                            clonelstNetwork.add(tmpList);
                        }else {
                            // If there is already present network in the list.
                            for (int j = 0; j < lstNetwork.size(); j++) {
                                List<String> createdList=lstNetwork.get(j);
                                for (int k = 0; k < createdList.size(); k++) {
                                    if(k==createdList.size()-1) {
                                        String nod = createdList.get(k);
                                        if (depend.equals(nod)) {
                                            tmpList = new ArrayList<String>();
                                            tmpList.addAll(createdList);
                                            tmpList.add(k + 1, node);
                                            clonelstNetwork.set(j, tmpList);
                                        }

                                    } else {
                                        String nod=createdList.get(k);
                                        // If path splits from node which is not a parent node.
                                        if(depend.equals(nod)) {
                                            tmpList= new ArrayList<String>();
                                            tmpList.addAll(createdList.subList(0, k+1));
                                            tmpList.add(k+1,node);
                                            String n=node;
                                            for(int ij=0;ij<inputList.size();ij++) {
                                                Activity object= inputList.get(ij);
                                                String act= object.getActivity();
                                                String dep= object.getDependency();

                                                String[] depArr=dep.split(",");
                                                for (String string : depArr) {
                                                    if(string.equals(n)) {
                                                        tmpList.add(act);
                                                        n=act;
                                                    }
                                                }
                                            }

                                            clonelstNetwork.add(j,tmpList);

                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }

            lstNetwork=new ArrayList<List<String>>(clonelstNetwork);
        }

        //Validation: Check for lstNetwork, starting node and end node must not be equal, otherwise cyclic
        for (List<String> list : lstNetwork) {
            if(list.size()>1) {
                if(list.get(0).equals(list.get(list.size()-1))) {
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

