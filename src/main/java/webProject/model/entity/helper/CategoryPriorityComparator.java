package webProject.model.entity.helper;

import webProject.model.entity.Job;

import java.util.Comparator;



public class CategoryPriorityComparator implements Comparator<Job> {

    @Override
    public int compare(Job o1, Job o2) {
        // ordering of priority is 1... 2... 3.... N...., where 1 is higher
        if(o1.getCategory().getPriority() > o2.getCategory().getPriority()) {
            return 1;
        } else if(o1.getCategory().getPriority() < o2.getCategory().getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }
}
