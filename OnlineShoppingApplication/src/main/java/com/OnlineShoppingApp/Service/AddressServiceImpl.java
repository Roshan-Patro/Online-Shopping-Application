package com.OnlineShoppingApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.OnlineShoppingApp.Entity.Address;
import com.OnlineShoppingApp.Entity.Customer;
import com.OnlineShoppingApp.Exception.AddressException;
import com.OnlineShoppingApp.Exception.CustomerException;
import com.OnlineShoppingApp.Repository.AddressDao;
import com.OnlineShoppingApp.Repository.CustomerDao;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao aDao;
	
	@Autowired
	private CustomerDao cDao;

	// 1. Adding Address
	@Override
	public Address addAnAddress(Address address) throws AddressException {
		Address savedAddress = aDao.save(address);

		if (savedAddress != null) {
			return savedAddress;
		}
		throw new AddressException("Address addition failed..! Please try again with valid details.");
	}

	// 2. Updating Address
	@Override
	public Address updateAddress(Address address) throws AddressException {
		Optional<Address> optAddress = aDao.findById(address.getAddressId());

		if (optAddress.isPresent()) {
			return aDao.save(address);
		}
		throw new AddressException("No address exists with the Id: " + address.getAddressId());
	}

	// 3. Removing Address by address
	@Override
	public Address removeAddressById(Integer addressId) throws AddressException {
		Optional<Address> optAddress = aDao.findById(addressId);

		if (optAddress.isPresent()) {
			Address existingAddress = optAddress.get();
			aDao.delete(existingAddress);

			return existingAddress;
		}
		throw new AddressException("No address exists with the Id: " + addressId);
	}

	// 4. Viewing all addresses
	@Override
	public List<Address> viewAllAddresses() throws AddressException {
		List<Address> addressList = aDao.findAll();

		if (addressList.size() != 0) {
			return addressList;
		}
		throw new AddressException("No address details found...!");
	}

	// 5. Viewing address by address Id
	@Override
	public Address viewAddressByAddressId(Integer addressId) throws AddressException {
		Optional<Address> optAddress = aDao.findById(addressId);

		if (optAddress.isPresent()) {
			Address existingAddress = optAddress.get();

			return existingAddress;
		}
		throw new AddressException("No address exists with the Id: " + addressId);
	}

	// 6. Viewing addresses by building name
	@Override
	public List<Address> viewAddressesByBuildingName(String buildingName) throws AddressException {
		List<Address> addressList = aDao.findByBuildingName(buildingName);

		if (addressList.size() != 0) {
			return addressList;
		}
		throw new AddressException("No address found with this building name.");
	}

	// 6. Viewing addresses with sorting by a property in ascending order
	@Override
	public List<Address> viewAddressesWithSortingAsc(String sortBy) throws AddressException {
		List<Address> addressList = aDao.findAll(Sort.by(Direction.ASC, sortBy));
		if (addressList.size() != 0) {
			return addressList;
		}
		throw new AddressException("No details found. Either the property is invalid or no records available.");
	}

	// 7. Viewing addresses of orders by pincode
	@Override
	public List<Address> addressesOfOrdersByPin(String pincode) throws AddressException {
		List<Address> allAddressesWithPin = aDao.findByPincode(pincode);

		if (allAddressesWithPin.size() != 0) {
			List<Address> onlyOrderAddresses = new ArrayList<>();
			for (Address address : allAddressesWithPin) {
				if (address.getOrderList().size() != 0) {
					onlyOrderAddresses.add(address);
				}
			}
			return onlyOrderAddresses;
		}
		throw new AddressException("No order address found with this pincode.");
	}

	// 8. Viewing addresses of customers by pincode
	@Override
	public List<Address> addressesOfCustomersByPin(String pincode) throws AddressException {
		List<Address> allAddressesWithPin = aDao.findByPincode(pincode);

		if (allAddressesWithPin.size() != 0) {
			List<Address> onlyCustomerAddresses = new ArrayList<>();
			for (Address address : allAddressesWithPin) {
				if (address.getCustomer() != null) {
					onlyCustomerAddresses.add(address);
				}
			}
			return onlyCustomerAddresses;
		}
		throw new AddressException("No customer address found with this pincode.");
	}

	// 9. Viewing addresses of orders by country and state
	@Override
	public List<Address> addressesOfOrdersByCountryState(String country, String state) throws AddressException {
		List<Address> allAddressesWithCountryState = aDao.findByCountryState(country, state);

		if (allAddressesWithCountryState.size() != 0) {
			List<Address> onlyOrderAddresses = new ArrayList<>();
			for (Address address : allAddressesWithCountryState) {
				if (address.getOrderList().size() != 0) {
					onlyOrderAddresses.add(address);
				}
			}
			return onlyOrderAddresses;
		}
		throw new AddressException("No order address found with this country and state.");
	}

	// 10. Viewing addresses of customers by country and state
	@Override
	public List<Address> addressesOfCustomersByCountryState(String country, String state) throws AddressException {
		List<Address> allCustomersWithCountryState = aDao.findByCountryState(country, state);

		if (allCustomersWithCountryState.size() != 0) {
			List<Address> onlyCustomerAddresses = new ArrayList<>();
			for (Address address : allCustomersWithCountryState) {
				if (address.getCustomer() != null) {
					onlyCustomerAddresses.add(address);
				}
			}
			return onlyCustomerAddresses;
		}
		throw new AddressException("No customer address found with this country and state.");
	}

	// 7. Viewing addresses with sorting by a property in descending order
	@Override
	public List<Address> viewAddressesWithSortingDsc(String sortBy) throws AddressException {
		List<Address> addressList = aDao.findAll(Sort.by(Direction.DESC, sortBy));
		if (addressList.size() != 0) {
			return addressList;
		}
		throw new AddressException("No details found. Either the property is invalid or no records available.");
	}

	// 8. Viewing addresses with pagination and sorting by a property in ascending
	// order
	// Page number starts from 0 for the available records, not 1.
	@Override
	public List<Address> viewAddressesWithPaginationAndSortingAsc(Integer pageNo, Integer pageSize, String sortBy)
			throws AddressException {
		Pageable pObj = PageRequest.of(pageNo, pageSize, Sort.by(Direction.ASC, sortBy));

		if (pObj.isPaged()) {
			Page<Address> addressPage = aDao.findAll(pObj);

			List<Address> allAddresses = addressPage.getContent();

			return allAddresses;
		}
		throw new AddressException(
				"No result found for this request...! Please, try again with another set of instructions! :)");

	}

	// 9. Viewing addresses with pagination and sorting by a property in descending
	// order
	@Override
	public List<Address> viewAddressesWithPaginationAndSortingDsc(Integer pageNo, Integer pageSize, String sortBy)
			throws AddressException {
		Pageable pObj = PageRequest.of(pageNo, pageSize, Sort.by(Direction.DESC, sortBy));

		if (pObj.isPaged()) {
			Page<Address> addressPage = aDao.findAll(pObj);

			List<Address> allAddresses = addressPage.getContent();

			return allAddresses;
		}
		throw new AddressException(
				"No result found for this request...! Please, try again with another set of instructions! :)");
	}

	// 10. Finding addresses by pincode
	@Override
	public List<Address> findAddressesByPincode(String pincode) throws AddressException {
		List<Address> addressList = aDao.findByPincode(pincode);

		if (addressList.size() != 0) {
			return addressList;
		}
		throw new AddressException("No address found with this pincode.");
	}

	@Override
	public Customer addAnAddressToCustomer(Integer addressId, Integer customerId)
			throws AddressException, CustomerException {
		Optional<Address> addOpt = aDao.findById(addressId);
		if(addOpt.isPresent()) {
			Address existingAddress = addOpt.get();
			Optional<Customer> customerOpt = cDao.findById(customerId);
			if(customerOpt.isPresent()) {
				Customer existingCustomer = customerOpt.get();
				existingAddress.setCustomer(existingCustomer);
				return cDao.save(existingCustomer);
			}
			throw new CustomerException("Invalid customer id: "+customerId);
		}
		throw new AddressException("Invalid address id.: "+addressId);
	}

}
