package ObjectOrientedDesign.onlinehotelbookingsystem;
/**
 * We have to design an online hotel booking system where a user 
 * can search a hotel in a given city and book it.
 */

class User {
    private long id;
    private String name;
    private Date birthday;
    private String email;
    private Address address;
}

class Hotel {
    private long hotelId;
    private String name;
    private Address address;
    List<Room> rooms;
}

class Address{
    private String city;
    private String zipcode;
}

class Room{
    private long id;
    private long hotelId;
    private RoomStatus roomStatus;

    public book(){
        this.roomStatus = RoomStatus.ORDERED;
    }
}

enum RoomStatus{
    EMPTY,
    ORDERED;
}

enum PaymentStatus {
    PAID,
    PENDING;
}

class Booking{
    private Hotel hotel;
    private User user;
    private List<Room> rooms;
    private List<Date> dates;
    public Booking(){}
    public bookRoom(User user, Hotel hotel, List<Room> rooms, List<Date> dates) {
        this.user = user;
        this.hotel = hotel;
        this.rooms = rooms;
        this.dates = dates;
        for (Room room : rooms){
            room.book();
        }
    }
}

