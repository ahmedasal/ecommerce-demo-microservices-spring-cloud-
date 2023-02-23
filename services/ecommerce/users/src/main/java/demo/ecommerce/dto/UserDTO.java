package demo.ecommerce.dto;

import demo.ecommerce.model.Customer;
import demo.ecommerce.model.Merchant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String userName;
    private String email;
    private String password;
    private String firstname;
    private String lastName;
    private String street;
    private String city;
    private String country;
    private String government;
    private String flatNo;


    public Customer toCustomerEntity() {
        Customer customer = new Customer();
        customer.setEmail(getEmail());
        customer.setFirstname(getFirstname());
        customer.setLastname(getLastName());
        customer.setCountry(getCountry());
        customer.setStreet(getStreet());
        customer.setCity(getCity());
        customer.setGovernment(getGovernment());
        customer.setFlatNo(getFlatNo());
        return customer;
    }
    public Merchant toMerchantEntity() {
        Merchant merchant = new Merchant();
        merchant.setEmail(getEmail());
        merchant.setFirstname(getFirstname());
        merchant.setLastname(getLastName());
        merchant.setCountry(getCountry());
        merchant.setStreet(getStreet());
        merchant.setCity(getCity());
        merchant.setGovernment(getGovernment());
        merchant.setFlatNo(getFlatNo());
        return merchant;
    }


}