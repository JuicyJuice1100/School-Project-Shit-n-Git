public class InsertPerson(){
    private Properties config;
        
    private void loadProperties() {
        try {
            FileInputStream file = new FileInputStream("config.properties");
            config = new Properties();
            config.load(file);
            file.close();
        } catch (Exception e) {
            System.out.println("An error occurred while trying to load config.properties.");
        }
    }

    public void InsertPerson()
}g