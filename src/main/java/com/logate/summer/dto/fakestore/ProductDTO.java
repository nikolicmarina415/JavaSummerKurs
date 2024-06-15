public class ProductDTO {

    private String name;
    private String shortDescription;
    private Double price;

    public ProductDTO() {
    }

    public ProductDTO(String name, String shortDescription, Double price) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
