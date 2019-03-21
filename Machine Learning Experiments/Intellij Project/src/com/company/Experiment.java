package com.company;

/**
 *  The Node of ExperimentList class.
 *  Stores Experiment details.
 *
 * @author Hikmet TUTUNCU
 * @since 10.03.2019
 *
 */
public class Experiment {
    /** explains the experimental setup */
    public String setup;
    /** represents the day of start     */
    public int day;
    /** represents the time of start    */
    public String time;
    /** indicates whether it is completed or not */
    public boolean completed;
    /** represents the output   */
    public float accuracy;
    /** Stores information next experiment for Linked-List structure.   */
    public Experiment next;
    /**  NextDay information. */
    public Experiment nextDay;
    /**  index of linked-list.    */
    public int index;

    /**
     * Constructor is initialize the Experiment informations.
     * */
    public Experiment(String setup,String time,boolean completed,int day, float accuracy)
    {
        this.setup = setup;
        this.day = day;
        this.time = time;
        this.accuracy = accuracy;
        this.completed = completed;
    }

    /**
    * Overriding of toString() method.
    * **/
    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", accuracy=" + accuracy +
                ", completed=" + completed +
                '}';
    }
}
