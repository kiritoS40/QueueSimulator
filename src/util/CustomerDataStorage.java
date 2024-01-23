/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author adoni
 */
public class CustomerDataStorage {
    private static final String[][] CUSTOMER_NAMES = {
        {"Raffy", "Zeller", "Philip", "John", "Ven", "Michael", "William", "James", "Robert", "Daniel",
        "Matthew", "Christopher", "Andrew", "Joseph", "David", "Brian", "Jackson", "Jonathan", "Nicholas", 
        "Ryan", "Brandon", "Justin", "Samuel", "Tyler", "Nathan", "Christian", "Ethan", "Jose", "Benjamin", 
        "Adam", "Noah", "Elijah", "Isaac", "Dylan", "Caleb", "Mason", "Evan", "Luke", "Jack", "Owen", 
        "Connor", "Aiden", "Gavin", "Liam", "Nathaniel", "Hunter", "Zachary", "Cooper", "Logan", "Rence"},
        
        {"Rose", "Emma", "Sophia", "Olivia", "Ava", "Mia", "Charlotte", "Amelia", "Evelyn", "Abigail",
        "Harper", "Isabella", "Sophie", "Emily", "Grace", "Lily", "Chloe", "Aria", "Scarlett", "Zoe", 
        "Layla", "Mila", "Luna", "Kimberly", "Avery", "Ella", "Ellie", "Aubrey", "Camila", "Sofia", 
        "Victoria", "Aurora", "Penelope", "Stella", "Nova", "Isla", "Paisley", "Naomi", "Mackenzie", 
        "Katherine", "Allison", "Lucy", "Madison", "Bella", "Leah", "Aaliyah", "Skylar", "Natalie", 
        "Kylie", "Maya", "Vera"}
    };


    private static final String[] SENIOR_IMAGE_NAMES = {"oldman", "oldman", "oldwoman"};
    private static final String[] PREGNANT_IMAGE_NAMES = {"pregnant", "pregnant"};
    private static final String[] PWD_IMAGE_NAMES = {"pwd1", "pwd2"};
    private static final String[] REGULAR_IMAGE_NAMES = {"boy", "man", "girl", "woman"};
    
    public static String getName(int sexIndex, int nameIndex) {
        return CUSTOMER_NAMES[sexIndex][nameIndex];
    }
    
    public static int getNumberOfNames() {
        return CUSTOMER_NAMES[0].length;
    }
    
    public static String[] getSeniorImageNames() {
        return SENIOR_IMAGE_NAMES;
    }
    
    public static String[] getPregnantImageNames() {
        return PREGNANT_IMAGE_NAMES;
    }
    
    public static String[] getPWDImageNames() {
        return PWD_IMAGE_NAMES;
    }
    
    public static String[] getRegularImageNames() {
        return REGULAR_IMAGE_NAMES;
    }
}
