import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.function.*;

/*
 * Columns in CalCareerData.dat:
 * ['Job_Listing', 'Work_Type/Schedule', 'Department', 'Location',
       'Publish_Date', 'Salary_min', 'Salary_max']
 */

public class Job {
    private String jobListing;
    private String workType;
    private String department;
    private String location;
    private String publishDate;
    private float salaryMin;
    private float salaryMax;
    
    public Job(String jobListing, String workType, String department, 
                String location, String publishDate,
                 float salaryMin, float salaryMax) {
        this.jobListing = jobListing;
        this.workType = workType;
        this.department = department;
        this.location = location;
        this.publishDate = publishDate;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
    }
    
    public String toString() {
        return String.format("Job Listing: %s\nWork Type: %s\nDepartment: %s\nLocation: %s\nPublish Date: %s\nSalary Range: $%.2f - $%.2f\n", 
            jobListing, workType, department, location, publishDate, salaryMin, salaryMax);
    }

    // Getters 
    public String getJobListing() {
        return jobListing;
    }

    public String getWorkType() {
        return workType;
    }

    public String getDepartment() {
        return department;
    }

    public String getLocation() {
        return location;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public float getSalaryMin() {
        return salaryMin;
    }

    public float getSalaryMax() {
        return salaryMax;
    }


    public boolean salaryIsWithinRange(float salary) {
        return salary >= salaryMin && salary <= salaryMax;
    }

    public static List<Job> loadJobs() {
        File f = new File("/workspaces/classcode/data/CalCareerData.dat");
        List<Job> jobs = new MArrayList<Job>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                Job j = new Job(parts[0], parts[1], parts[2], parts[3], parts[4], Float.parseFloat(parts[5]), Float.parseFloat(parts[6]));
                jobs.add(j);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    // How many jobs start with a specific prefix
    public static int numJobsStartingWithChar(List<Job> jobs, String pre) {
        return numJobsMatchingPredicate(jobs, j -> j.getJobListing().startsWith(pre));
    }

    // How many jobs fit a criterion
    public static int numJobsMatchingPredicate(List<Job> jobs, Predicate<Job> filter) {
        return (int) jobs.stream()
            .filter(filter)
            .count();
    }

    public static int numJobsMatchingPredicateIterator(List<Job> jobs, Predicate<Job> filter) {
        int count = 0; 
        Iterator<Job> it = jobs.iterator();
        while (it.hasNext()) {
            Job j = it.next();
            if(filter.test(j)) count++;
        }
        return count;
    }

    public static int numJobsMatchingPredicateEnhancedForLoop(List<Job> jobs, Predicate<Job> filter) {
        int count = 0;
        for (Job j : jobs) {
            if (filter.test(j)) count++;
        }
        return count;
    }

    public static int numJobsMatchingPredicateIndex(List<Job> jobs, Predicate<Job> filter) {
        int count = 0;
        for (int i = 0; i < jobs.size(); i++) {
            if (filter.test(jobs.get(i))) count++;
        }
        return count;
    }


    public static void main(String[] args) {
        List<Job> jobs = loadJobs();
        
        System.out.println(String.format("Number of jobs starting with 'A': %d", numJobsStartingWithChar(jobs, "A")));
        System.out.println(String.format("Number of jobs starting with 'FISH': %d", numJobsStartingWithChar(jobs, "FISH")));

        System.out.println(String.format("Number of jobs with min salary of six figures: %d", numJobsMatchingPredicate(jobs, j -> j.getSalaryMin() > 99999)));

        System.out.println("Jobs with min six figure salary:");
        jobs.stream()
            .filter(j -> j.getSalaryMin() > 99999)
            .forEach(System.out::println);

        System.out.println("Jobs with top five highest max salary:");
        jobs.stream()
            .sorted((j1, j2) -> (int) (j2.getSalaryMax() - j1.getSalaryMax()))
            .limit(5)   
            .forEach(System.out::println);

        System.out.println("With an iterator:");
        Collections.sort(jobs, Collections.reverseOrder((j1, j2) -> Float.compare(j1.getSalaryMax(), j2.getSalaryMax())));
        Iterator<Job> it = jobs.iterator();
        for (int i = 0; i < 5; i++) {
            if (it.hasNext()) System.out.println(it.next());
        }

        System.out.println("Are there any jobs that are intermittent with a salary greater than 30K? " +  
            jobs.stream()
            .anyMatch(j -> j.getWorkType().contains("Intermittent") && j.getSalaryMin() >= 30000));

        // What is the average min salary for intermittent jobs?
        double averageIntermittentSalary = jobs.stream()
            .filter(j -> j.getWorkType().contains("Intermittent"))
            .mapToDouble(Job::getSalaryMin)
            .average()
            .orElse(0.0);
        System.out.println("Average salary for intermittent jobs: " + averageIntermittentSalary);

        System.out.print("With an iterator: ");
        it = jobs.iterator();
        boolean any = false;
        while (it.hasNext()) {
            Job j = it.next();
            if (j.getWorkType().contains("Intermittent") && j.getSalaryMin() >= 30000) {
                System.out.println("true");
                any = true;
                break;
            }
        }
        if (!any) System.out.println("false");

        // Get a list of jobs in Sacramento County
        List<Job> sacramentoJobs = jobs.stream()
            .filter(j -> j.getLocation().equals("Sacramento County"))
            .collect(Collectors.toList());

        // How many jobs have "DENTAL" in the listing?
        long dentalJobsCount = jobs.stream()
            .filter(j -> j.getJobListing().contains("DENTAL"))
            .count();
        System.out.println("Number of jobs with 'DENTAL' in the listing: " + dentalJobsCount);

        // What is the average max salary for jobs in Sacramento County?
        double averageSacramentoMaxSalary = jobs.stream()
            .filter(j -> j.getLocation().equals("Sacramento County"))
            .mapToDouble(Job::getSalaryMax)
            .average()
            .orElse(0.0);
        System.out.println("Average max salary for jobs in Sacramento County: " + averageSacramentoMaxSalary);

    
    }

}