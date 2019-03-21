package com.company;
import java.util.Iterator;

/**
 *  ExperimentList class to keep track of some machine
 * learning experiments and their results. Represents a single Linked-List.
 *
 * @author Hikmet TUTUNCU
 * @since 10.03.2019
 *
 */
public class ExperimentList implements Iterable<Experiment> {
    /**
     * Keeps head of linked-list.
     */
    public Experiment head;
    /**
     *  Inserting experiment to the end of the day.
     * @param e Experiment data to add linked-list structure.
     * */
    public void addExp(Experiment e)
    {
        if( head == null )
        {
            head = e;           /* If the linked-list is empty, */
            head.next = null;
            head.nextDay = null;    /* The first node's nextDay information */
            head.index = 0;
        }
        else
        {
            Experiment newNode;
            if( head.day > e.day )  /*  If new experiment's day smaller than first experiment of list;         */
            {                       /*  Creates a newNode and add to linked-list according to day information. */
                newNode = e;
                newNode.next = head;
                head = newNode;
                head.index = 0;
                Experiment tempNode = head;
                while ( tempNode.next != null )
                {
                    tempNode.next.index++;
                    tempNode = tempNode.next;
                }
            }
            else if ( head.day == e.day)
            {                       /*  If new experiment's day equals to first experiment of the list;     */
                Experiment temp = head;
                newNode = e;        /*  Create a newNode and add to linked-list.                            */
                while ( temp.next != null) {
                    if ( temp.next.day > e.day )
                        break;      /*  Goes to end of the list and add experiment there.                   */
                    temp = temp.next;
                }
                newNode.next = temp.next;
                temp.next = newNode;
            }
            else
            {         /*            Creates a newNode and search new Experiment it in linked-list.          */
                Experiment tempNode = head;
                while (tempNode.next != null )
                    tempNode = tempNode.next;
                if ( e.day == tempNode.day  )
                {    /*             If new experiment's day equals to last node                              */
                    tempNode.next = e;  /*  Adds it to end of the linked-list.                               */
                    e.index = tempNode.index +1 ;
                }
                else if ( e.day > tempNode.day )
                {                   /*  Adds it to end of the linked-list.                                  */
                    tempNode = head;
                    while (tempNode.next != null )
                        tempNode = tempNode.next;
                    tempNode.next = e;
                    Experiment iter = head;
                    while ( tempNode.day != iter.day )
                        iter = iter.next;   /*  The last item is new Experiment.                            */
                    tempNode.next.index = tempNode.index +1;
                }
                else // if ( e.day < tempNode.day )
                {       /*  If new experiment's day smaller than last experiment;                           */
                    Experiment tempNode2 = head;
                    while ( tempNode2.next != null )
                    {   /*  If new experiment's day info is between a two Experiment , adds it there.       */
                        if ( tempNode2.day <= e.day && tempNode2.next.day > e.day )
                            break;
                        tempNode2 = tempNode2.next;
                    }
                    e.next = tempNode2.next;
                    tempNode2.next = e;

                }
            }

        }
        /*  This part is evaluating "NextDay information" of each experiment in the Linked-List.             */
        Experiment i1 = head;
        Experiment i2 = head;
        int setIndex = 0;
        while ( i1.next != null )
        {
            if ( i1.day != i1.next.day )
            {   /*  If day info is change in another experiment, the program understands that is nextDay.    */
                i2.nextDay = i1.next;
                i2 = i2.nextDay;
            }
            i1 = i1.next;
            i1.index = setIndex;    /*  The part is calculating and storing index information of experiments. */
            setIndex++;
        }

    }

    /**
     *  Get the experiment with the given day and position
     *
     * @param day The parameter is day information of experiment.
     * @param idx The index of linked-list, wanted from getting Experiment.
     * @return temp is Experiment that wanted.
     * */
    public Experiment getExp(int day,int idx)
    {
        Experiment temp = head;
        while( temp.next != null )
        {
            if( temp.index == idx && temp.day == day )
                break;
            temp = temp.next;
        }
        return temp;
    }

    /**
     * Set the experiment with the given day and position.
     * @param day The parameter is day information of experiment.
     * @param idx The index of linked-list, wanted from setting Experiment.
     * @param e    is Experiment that wanted to set.
     * */
    public void setExp(int day,int idx,Experiment e)
    {
        Experiment temp = head;
        while ( temp.next != null )
        {
            if( temp.day == day && temp.index == idx )
                break;
            temp = temp.next;
        }
        temp.day = e.day;               /*          Assigning to experiment fields.             */
        temp.setup = e.setup;
        temp.accuracy = e.accuracy;
        temp.time = e.time;
        temp.completed = e.completed;
    }

    /**
     * Remove the experiment specified as index from given day.
     * @param day The parameter is day information of experiment.
     * @param index The index of linked-list, wanted from removing Experiment.
     * */
    public void removeExp(int day,int index)
    {
        if( index == 0 )
        {   /*      Removing of the first Experiment of the Linked-List.                    */
            Experiment temp = head;
            head = temp.next;
        }
        else
        {   /*  Removing another experiment .                                                */
            Experiment temp = head;
            while ( temp.next != null )
            {   /*  If the experiment that wanted to remove is between two experiment, find and put there.  */
                if ( temp.next.day == day && temp.next.index == index )
                    break;
                temp = temp.next;
            }
            if (temp.next != null )
                temp.next = temp.next.next;
        }
        /*  This part is evaluating "NextDay information" of each experiment in the Linked-List.             */
        Experiment i1 = head;
        Experiment i2 = head;
        int setIndex = 0;
        while ( i1.next != null )
        {
            if ( i1.day != i1.next.day )
            {
                i2.nextDay = i1.next;
                i2 = i2.nextDay;
            }
            i1 = i1.next;
            i1.index = setIndex;    /*  The part is calculating and storing index information of experiments. */
            setIndex++;
        }
    }

    /**
     * Prints the Linked-List according to given day.
     * @param day The parameter is day information of experiment.
     * */
    public void listExp(int day)
    {
        Experiment temp = head;
        while ( temp.next != null )
        {
            if ( temp.day == day )
                System.out.println(temp.setup);
            temp = temp.next;
        }
    }

    /**
     * Removes the given day from Linked-List.
     * @param day The parameter is day information of experiment.
     * */
    public boolean removeDay(int day)
    {
        Experiment temp = head;
        while ( temp.next != null )
        {
            if ( temp.day == day )
                removeExp(day,temp.index);
            temp = temp.next;
        }
        return true;
    }

    /**
     * Orders the given day from Linked-List.
     * @param day The parameter is day information of experiment.
     * */
    public void orderDay(int day)
    {
        Experiment temp = head;
        while (temp.next != null)
        {
            if (temp.day == day)
                break;
            temp = temp.next;
        }
        Experiment temp2 = temp;
        Experiment temp3 = temp.next;
        while ( temp2.day == day )
        {
            while ( temp3.day == day )
            {   /*  Copies all the experiment informations to another according to accurancy field.     */
                if (temp2.accuracy > temp3.accuracy )
                {
                    String tempSetup = temp2.setup;
                    String tempTime = temp2.time;
                    boolean tempCompleted = temp2.completed;
                    float tempAccuracy = temp2.accuracy;

                    temp2.setup = temp3.setup;
                    temp2.time = temp3.time;
                    temp2.completed = temp3.completed;
                    temp2.accuracy = temp3.accuracy;

                    temp3.setup = tempSetup;
                    temp3.time = tempTime;
                    temp3.completed = tempCompleted;
                    temp3.accuracy = tempAccuracy;
                }
                temp3 = temp3.next;
            }
            temp2 = temp2.next;
            temp3 = temp2.next;
        }
    }

    /**
     *  Order Experiments according to accuracy,
     *  but not changes the original linked-list.
     *  @return returns a New Linked-List.
     * */
    public ExperimentList orderExperiments()
    {
        Experiment temp2 = head;
        Experiment temp3 = head;
        ExperimentList newList = new ExperimentList();
        newList.head = head;
        while ( temp2.next != null )
        {
            while (temp3 != null)
            {   /*  Similar to orderDay method. Copies information according to accuracy sorting. */
                if (temp2.accuracy > temp3.accuracy )
                {
                    String tempSetup = temp2.setup;
                    String tempTime = temp2.time;
                    boolean tempCompleted = temp2.completed;
                    float tempAccuracy = temp2.accuracy;

                    temp2.setup = temp3.setup;
                    temp2.time = temp3.time;
                    temp2.completed = temp3.completed;
                    temp2.accuracy = temp3.accuracy;

                    temp3.setup = tempSetup;
                    temp3.time = tempTime;
                    temp3.completed = tempCompleted;
                    temp3.accuracy = tempAccuracy;
                }
                temp3 = temp3.next;
            }
            temp2 = temp2.next;
            temp3 = temp2.next;
        }
        return newList;
    }

    /**
    * Prints all the Linked-List structure.
    * **/
    public void listAll()
    {
        System.out.println("List experiment view:");
        Experiment last = head;
        while( last != null) {
            System.out.println(last.toString());
            last = last.next;
        }
        System.out.println("List day view:");
        last = head;
        while( last != null) {
            System.out.println(last.toString());
            last = last.nextDay;
        }
    }

    /**
    * @return returns it how many experiments in the Linked-List.
    * **/
    private int getIndex()
    {
        int index = 0;
        Experiment temp = head;
        while (temp.next != null)
        {
            index++;
            temp = temp.next;
        }
        return index;
    }


    /**
    * Override operation for iterable interface .
    * **/
    @Override
    public Iterator<Experiment> iterator() {
        return new Iterator<Experiment>() {
            private int index = 0;
            Experiment temp = head;
            @Override
            public boolean hasNext() {
                return index <= getIndex();
            }

            @Override
            public Experiment next() {
                if (hasNext())
                {
                    Experiment tempHead = temp;
                    temp = temp.next;
                    index++;
                    return tempHead ;
                }
                return null;
            }
        };
    }


}
