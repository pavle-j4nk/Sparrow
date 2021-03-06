package com.sparrow.backend.data;

import com.sparrow.backend.model.*;
import com.sparrow.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private String BASE_64_IMAGE;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private PriceListItemRepository priceListItemRepository;

    @Autowired
    private ExtraServicesRepository extraServicesRepository;

    @Autowired
    private HotelServicesRepository hotelServicesRepository;

    @Autowired
    private HotelReservationRepository hotelReservationRepository;

    @Autowired
    private RentACarRepository rentACarRepository;

    @Autowired
    private DealershipRepository dealershipRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarReservationRepository carReservationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if (alreadySetup)
                return;
            Privilege readPrivilege
                    = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege
                    = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

            List<Privilege> adminPrivileges = Arrays.asList(
                    readPrivilege, writePrivilege);
            createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
            createRoleIfNotFound("ROLE_HOTEL_ADMIN", adminPrivileges);
            createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
            createRoleIfNotFound("ROLE_AIRLINE_ADMIN", adminPrivileges);

            Role roleUser = roleRepository.findByName("ROLE_USER").get();
            Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
            Role hotelAdminRole = roleRepository.findByName("ROLE_HOTEL_ADMIN").get();
            Role airlineAdminRole = roleRepository.findByName("ROLE_AIRLINE_ADMIN").get();

            User user = new User();
            user.setFirstName("Test");
            user.setLastName("Test");
            user.setPassword(passwordEncoder.encode("123"));
            user.setEmail("test@test.com");
            user.setUsername("test");
            user.setAddress("Unknown");
            user.setRole(roleUser);
            user.setEnabled(true);
            userRepository.save(user);

            User u1 = new User("pavle.jankovic", "pavle.gp@gmail.com", "Pavle", "Jankovic", "Babanovacka bb", passwordEncoder.encode("123"), true, roleUser);
            User u2 = new User("marko.ristic", "marko.ristic@gmail.com", "Marko", "Ristic", "Topolska 18", passwordEncoder.encode("123"), true, roleUser);
            User u3 = new User("aleksandar.vujasinovic", "aleksandar.vujasinov@gmail.com", "Aleksandar", "Vujasinovic", "Laze Stajica 16", passwordEncoder.encode("123"), true, roleUser);
            User hotelAdmin = new User("hotel.admin", "hotel_admin@sparrow.com", "Hotel", "Admin", "Hotel Admin Address 0", passwordEncoder.encode("123"), true, hotelAdminRole);
            User admin = new User("sysadmin", "admin@admin.com", "Bog", "Boziji", "Nebeska 12", passwordEncoder.encode("123"), true, adminRole);
            User airlineAdmin = new User("aa", "ad11min@admin.com", "Letac", "Presratac", "Nebeska 12", passwordEncoder.encode("123"), true, airlineAdminRole);

            Address a1 = new Address("Danila Kisa 44, 21000, Novi Sad", 19.8313, 45.2413);
            Address a2 = new Address("Brace Ribnikar 17, 21000, Novi Sad", 19.8356, 45.24532);
            Address a3 = new Address("Topolska 18, 11000, Beograd", 20.47, 44.79);
            Address a4 = new Address("Gavrila Principa 3, 11000, Beograd", 20.432525, 44.83251);
            addressRepository.saveAll(Arrays.asList(a1, a2, a3, a4));

            userRepository.save(hotelAdmin);

            Hotel h1 = new Hotel("Plaza", "The Plaza Hotel is a landmarked 20-story luxury hotel and condominium apartment building in the Midtown Manhattan neighborhood of Manhattan, New York City. It opened in 1907 and is now owned by Katara Hospitality.", hotelAdmin, a1);
            h1.setImage(BASE_64_IMAGE);
            h1.setAdmin(hotelAdmin);
            hotelRepository.saveAll(Arrays.asList(h1));

            ExtraService e1 = new ExtraService();
            e1.setName("Wellness");
            ExtraService e2 = new ExtraService();
            e2.setName("Swimming pool");
            ExtraService e3 = new ExtraService();
            e3.setName("Spa");
            ExtraService e4 = new ExtraService();
            e4.setName("Breakfast");
            ExtraService e5 = new ExtraService();
            e5.setName("Transfer to airport");
            ExtraService e6 = new ExtraService();
            e6.setName("Transfer from airport");
            ExtraService e7 = new ExtraService();
            e7.setName("Gym");
            extraServicesRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));

            HotelServices hs1 = new HotelServices();
            hs1.setExtraService(e1);
            hs1.setHotel(h1);
            hs1.setPrice(20.0);

            HotelServices hs2 = new HotelServices();
            hs2.setExtraService(e2);
            hs2.setHotel(h1);
            hs2.setPrice(5.0);

            HotelServices hs3 = new HotelServices();
            hs3.setExtraService(e3);
            hs3.setHotel(h1);
            hs3.setPrice(10.0);

            hotelServicesRepository.saveAll(Arrays.asList(hs1, hs2, hs3));

            Room r1 = new Room("Single room", 1, h1, 1, true);
            Room r2 = new Room("Double room", 2, h1, 1, true);
            Room r3 = new Room("Special double room", 3, h1, 1, true);
            Room r4 = new Room("Family room", 5, h1, 2, true);
            Room r5 = new Room("Small family room", 4, h1, 2, true);
            Room r6 = new Room("King's Apartment", 7, h1, 3, true);
            roomRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6));

            Set<PriceList> plazaPriceLists = new HashSet<>();
            PriceList pl1 = new PriceList(h1);
            priceListRepository.save(pl1);

            PriceListItem p1 = new PriceListItem(r1, 100.0, pl1);
            PriceListItem p2 = new PriceListItem(r2, 120.0, pl1);
            PriceListItem p3 = new PriceListItem(r3, 130.0, pl1);
            PriceListItem p4 = new PriceListItem(r4, 140.0, pl1);
            PriceListItem p5 = new PriceListItem(r5, 150.0, pl1);
            PriceListItem p6 = new PriceListItem(r6, 160.0, pl1);

            List<PriceListItem> plitems = Arrays.asList(p1, p2, p3, p4, p5, p6);
            priceListItemRepository.saveAll(plitems);
            pl1.setItems(new HashSet<>(plitems));
            priceListRepository.save(pl1);

            h1.setPriceLists(new HashSet<>(Arrays.asList(pl1)));

            userRepository.saveAll(Arrays.asList(u1, u2, u3, admin, hotelAdmin, airlineAdmin));
            hotelRepository.save(h1);

            HotelReservation hr = new HotelReservation();
            HotelReservation hr1 = new HotelReservation();

            hr1.setStart(new Date(1569948590000L));
            hr1.setEnd(new Date(1570726190000L));

            hr.setStart(new Date(1565388000000L));
            hr.setEnd(new Date(1565820000000L));

            Set<Room> reservationRooms = new HashSet<>();
            reservationRooms.add(r1);

            Set<HotelServices> hotelServices = new HashSet<>();
            hotelServices.add(hs1);
            hr.setRooms(reservationRooms);
            hr.setHotelServices(hotelServices);
            hr.setUser(u3);
            hr.setPrice(102.0);
            hr1.setRooms(reservationRooms);
            hr1.setHotelServices(hotelServices);
            hr1.setUser(u3);
            hr1.setPrice(102.0);
            hotelReservationRepository.save(hr);
            hotelReservationRepository.save(hr1);

            Airline airline = new Airline();
            Address airlineAdd = new Address();
            airlineAdd.setLatitude(10);
            airlineAdd.setLongitude(10);
            airline.setAddress(airlineAdd);
            airline.setDescription("Airline bla bla bla");
            airline.setName("Airline1");
            airline.setAdmin(airlineAdmin);
            airlineRepository.save(airline);


            Destination d1 = new Destination();
            d1.setName("Belgrade");
            d1.setCode("BEG");
            Destination d2 = new Destination();
            d2.setName("Paris");
            d2.setCode("CDG");
            Destination d3 = new Destination();
            d3.setName("Moscov");
            d3.setCode("VKO");
            Destination d4 = new Destination();
            d4.setCode("ATH");
            d4.setName("Athens");
            Destination d5 = new Destination();
            d5.setName("Amsterdam");
            d5.setCode("AMS");

            destinationRepository.saveAll(Arrays.asList(d1, d2, d3, d4, d5));

            RentACar rentACar1 = new RentACar("Marko", "Najbolji Rent-A-Car servis.", admin, a2, "Novi Sad");
            RentACar rentACar2 = new RentACar("Djuro", "Nista posebno", admin, a1, "Novi Sad");
            RentACar rentACar3 = new RentACar("Pero", "Mozda su auta losa ali su skupa ", admin, a3, "Beograd");

            rentACarRepository.saveAll(Arrays.asList(rentACar1, rentACar2, rentACar3));


            Dealership ds1 = new Dealership("Filijala1", rentACar1, a1);
            Dealership ds2 = new Dealership("filijala2", rentACar2, a1);
            Dealership ds3 = new Dealership("filijala3", rentACar3, a2);
            Dealership ds4 = new Dealership("filijala4", rentACar1, a3);
            Dealership ds5 = new Dealership("filijala5", rentACar2, a2);

            dealershipRepository.saveAll(Arrays.asList(ds1, ds2, ds3, ds4, ds5));

            Car car1 = new Car("Diesel", "Peugeot", "3008", 2017, 49, rentACar1, 5);
            Car car2 = new Car("Electric", "Tesla", "Roadster", 2019, 35, rentACar1, 2);
            Car car3 = new Car("Petrol", "BMW", "305", 2013, 60, rentACar1, 5);
            Car car4 = new Car("Petrol", "Mercedes-Benz", "A180", 2013, 60, rentACar2, 5);
            Car car5 = new Car("Petrol", "Lexus", "315", 2016, 55, rentACar2, 5);
            Car car6 = new Car("Diesel", "Opel", "Kadet", 1998, 80, rentACar3, 5);
            Car car7 = new Car("Petrol", "VW", "Golf 4", 2000, 85, rentACar3, 5);


            carRepository.saveAll(Arrays.asList(car1, car2, car3, car4, car5, car6, car7));


            alreadySetup = true;

        } catch (Throwable ignored) {
            ignored.printStackTrace();
        }
    }

    // @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    protected Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    // @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    protected Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Optional<Role> roleOpt = roleRepository.findByName(name);
        if (!roleOpt.isPresent()) {
            Role role = new Role(name);
            role.setPrivileges(privileges);
            return roleRepository.save(role);
        }
        return roleOpt.get();
    }
}
