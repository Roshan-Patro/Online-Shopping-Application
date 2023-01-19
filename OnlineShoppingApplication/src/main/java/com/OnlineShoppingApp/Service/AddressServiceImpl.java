package com.OnlineShoppingApp.Service;

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
import com.OnlineShoppingApp.Exception.AddressException;
import com.OnlineShoppingApp.Repository.AddressDao;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressDao aDao;
	

	// 1. Adding Address
	@Override
	public Address addAnAddress(Address address) throws AddressException{
		Address savedAddress = aDao.save(address);
		
		if(savedAddress!=null) {
			return savedAddress;
		}
		throw new AddressException("Address addition failed..! Please try again with valid details.");
	}

	// 2. Updating Address
	@Override
	public Address updateAddress(Address address) throws AddressException {
		Optional<Address> optAddress = aDao.findById(address.getAddressId());
		
		if(optAddress.isPresent()) {
			return aDao.save(address);
		}
		throw new AddressException("No address exists with the Id: "+address.getAddressId());
	}

	// 3. Removing Address by address
	@Override
	public Address removeAddress(Address address) throws AddressException {
		Optional<Address> optAddress = aDao.findById(address.getAddressId());
		
		if(optAddress.isPresent()) {
			Address existingAddress = optAddress.get();
			aDao.delete(existingAddress);
			
			return existingAddress;
		}
		throw new AddressException("No address exists with the Id: "+address.getAddressId());
	}

	// 4. Viewing all addresses
	@Override
	public List<Address> viewAllAddresses() throws AddressException {
		List<Address> addressList = aDao.findAll();
		
		if(addressList.size()!=0) {
			return addressList;
		}
		throw new AddressException("No address details found...!");
	}

	// 5. Viewing address by address Id
	@Override
	public Address viewAddressByAddressId(Integer addressId) throws AddressException {
		Optional<Address> optAddress = aDao.findById(addressId);
		
		if(optAddress.isPresent()) {
			Address existingAddress = optAddress.get();
	
			return existingAddress;
		}
		throw new AddressException("No address exists with the Id: "+addressId);
	}

	// 6. Viewing addresses with sorting by a property in ascending order
	@Override
	public List<Address> viewAddressesWithSortingAsc(String sortBy) throws AddressException {
		List<Address> addressList = aDao.findAll(Sort.by(Direction.ASC, sortBy));
		if(addressList.size()!=0) {
			return addressList;
		}
		throw new AddressException("No details found. Either the property is invalid or no records available.");
	}
	
	// 7. Viewing addresses with sorting by a property in descending order
	@Override
	public List<Address> viewAddressesWithSortingDsc(String sortBy) throws AddressException {
		List<Address> addressList = aDao.findAll(Sort.by(Direction.DESC, sortBy));
		if(addressList.size()!=0) {
			return addressList;
		}
		throw new AddressException("No details found. Either the property is invalid or no records available.");
	}
	
	// 8. Viewing addresses with pagination and sorting by a property in ascending order
	// Page number starts from 0 for the available records, not 1.
	@Override
	public List<Address> viewAddressesWithPaginationAndSortingAsc(Integer pageNo, Integer pageSize, String sortBy)
			throws AddressException {
		Pageable pObj = PageRequest.of(pageNo, pageSize, Sort.by(Direction.ASC, sortBy));
		
		if(pObj.isPaged()) {
			Page<Address> addressPage = aDao.findAll(pObj);
			
			List<Address> allAddresses = addressPage.getContent();
			
			return allAddresses;
		}
		throw new AddressException("No result found for this request...! Please, try again with another set of instructions! :)");
		
	}

	// 9. Viewing addresses with pagination and sorting by a property in descending order
	@Override
	public List<Address> viewAddressesWithPaginationAndSortingDsc(Integer pageNo, Integer pageSize, String sortBy)
			throws AddressException {
		Pageable pObj = PageRequest.of(pageNo, pageSize, Sort.by(Direction.DESC, sortBy));
		
		if(pObj.isPaged()) {
			Page<Address> addressPage = aDao.findAll(pObj);
			
			List<Address> allAddresses = addressPage.getContent();
			
			return allAddresses;
		}
		throw new AddressException("No result found for this request...! Please, try again with another set of instructions! :)");
	}

	// 10. Finding addresses by pincode
	@Override
	public List<Address> findAddressesByPincode(String pincode) throws AddressException {
		List<Address> addressList = aDao.findByPincode(pincode);
		
		if(addressList.size()!=0) {
			return addressList;
		}
		throw new AddressException("No address found with this pincode.");
	}

	

	
	
	
}
