import java.util.*;
import java.util.stream.Collectors;

// banana_quality.csv has the following columns:
// Size,Weight,Sweetness,Softness,HarvestTime,Ripeness,Acidity,Quality
// All are floats except Quality, which is a string

public class Banana {
    float size, weight, sweetness, softness, harvestTime, ripeness, acidity;
    String quality;
    static final String GOOD = "good", BAD = "bad";

    public Banana(float size, float weight, float sweetness, float softness, 
            float harvestTime, float ripeness, float acidity, String quality) {
        this.size = size;
        this.weight = weight;
        this.sweetness = sweetness;
        this.softness = softness;
        this.harvestTime = harvestTime;
        this.ripeness = ripeness;
        this.acidity = acidity;
        this.quality = quality;
    }

    public String toString() {
        return String.format("Size: %.2f\nWeight: %.2f\nSweetness: %.2f\nSoftness: %.2f\nHarvest Time: %.2f\nRipeness: %.2f\nAcidity: %.2f\nQuality: %s\n", 
            size, weight, sweetness, softness, harvestTime, ripeness, acidity, quality);
    }

    public static List<Banana> loadBananas(String filename) {
        List<Banana> bananas = new MArrayList<>();
        try {
            Scanner scanner = new Scanner(new java.io.File(filename));
            scanner.nextLine(); // Skip the header
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                bananas.add(new Banana(Float.parseFloat(line[0]), Float.parseFloat(line[1]), 
                    Float.parseFloat(line[2]), Float.parseFloat(line[3]), 
                    Float.parseFloat(line[4]), Float.parseFloat(line[5]), 
                    Float.parseFloat(line[6]), line[7]));
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return bananas;
    }

    public float getSize() {
        return size;
    }

    public float getWeight() {
        return weight;
    }

    public float getSweetness() {
        return sweetness;
    }

    public float getSoftness() {
        return softness;
    }

    public float getHarvestTime() {
        return harvestTime;
    }

    public float getRipeness() {
        return ripeness;
    }

    public float getAcidity() {
        return acidity;
    }

    public String getQuality() {
        return quality;
    }

    public boolean isGood() {
        return quality.equals(GOOD);
    }

    public static void main(String[] args) {
        List<Banana> bananas = loadBananas("../data/banana_quality.csv");

    }

}