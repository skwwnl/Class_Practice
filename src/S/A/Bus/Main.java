package S.A.Bus;

class Transport{
    protected int transNum;
    protected int fuel;
    protected int speed;
    protected int max_Passengers;
    protected int cur_Passengers;
    protected String state;

    Transport(int num){
        fuel = 100;
        speed = 0;
        setTransNum(num);
    }
    // 추상클래스가 아니므로 메소드 구현을 다 해야합니다.
    public void driving(){
        if(fuel >= 10 )
            state = "운행";
    }
    public int getCurFuel(){
        return fuel;
    }
    public void setCurFuel(int fuel){
        this.fuel += fuel;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
    public void set_onBoarding(int cur_Passengers){
        this.cur_Passengers += cur_Passengers;
    }
    public void changeSpeed(int speed){
        if(fuel < 10)
            System.out.println("주유량을 확인해 주세요.");
        this.speed += speed;
    }
    public void setTransNum(int transNum){
        this.transNum = transNum;
    }
}

class Bus extends Transport{
    protected final int busfee;

    Bus(int num){
        super(num);
        max_Passengers = 30;
        state = "운행";
        busfee = 1000;
        System.out.println(num + "번 버스 객체");
    }

    @Override
    public void set_onBoarding(int pass) {
        if (pass + cur_Passengers > max_Passengers)
            System.out.println("최대 승객 수 초과입니다.");
        else if (!state.equals("운행")) {
            System.out.println("운행 중이 아닙니다");
        } else {
            cur_Passengers += pass;
            System.out.println("탑승 승객 수 : " + cur_Passengers);
            System.out.println("잔여 승객 수 : " + (max_Passengers-cur_Passengers));
            System.out.println("요금 확인 : " + pass * busfee);
        }
    }
    public void changeState(){
        if(super.fuel<10) {
            super.state = "차고지행";
            System.out.println("주유가 필요합니다");
        } else if (state.equals("운행")) {
            super.state = "차고지행";
        } else
            super.state = "운행";
    }
}

class Taxi extends Transport{
    private String dest;
    private int defaultDis;
    private int defaultFee;
    private int perFee;
    private int totalFee;

    Taxi(int n){
        super(n);
        cur_Passengers = 0;
        max_Passengers = 4;
        defaultFee = 3000;
        perFee = 1000;
        defaultDis = 1;
        totalFee = 0;
        state = "일반";
        System.out.println(n + "번 택시");
    }

    // 운행 시작
    public void driving(){
        if(fuel < 10 )
            state = "탑승불가";
    }
    // 상태 변경
    public void changeState(){
        if(state.equals("일반"))
            state ="운행 중";
        if(fuel < 10){
            state = "운행 불가";
            System.out.println("주유가 필요합니다.");
        }
    }
    public void set_onBoarding(int pass) {
        if (pass + super.cur_Passengers > max_Passengers)
            System.out.println("최대 승객 수 초과입니다.");
        else {
            cur_Passengers += pass;
            System.out.println("탑승 승객 수 : " + cur_Passengers);
            System.out.println("잔여 승객 수 : " + (max_Passengers-cur_Passengers));
        }
    }
    public void acrossLoad(String dest, int disNum){
        this.dest = dest;
        System.out.println("기본 요금 확인 = "+ defaultFee);
        System.out.println("목적지 = "+ this.dest);
        System.out.println("목적지까지 거리 = "+ disNum);
        System.out.println("지불할 요금 = "+(defaultFee+(disNum-defaultDis)*perFee));
        setState("운행중");
        System.out.println("상태 = "+getState());
        totalFee+=(defaultFee+(disNum-defaultDis)*perFee);
    }
    public void setCurFuel(int fuel){
        super.fuel += fuel;
        System.out.println("주유량 = "+getCurFuel());
        System.out.println("누적 요금 = "+totalFee);
        set_onBoarding(-cur_Passengers);
        if(getCurFuel() < 10){
            System.out.println("주유 필요");
            setState("운행 불가");
            System.out.println("상태 = "+getState());
        }
    }
}
public class Main {
    public static void main(String[] args) {
//        Bus bus1 = new Bus(1);
//
//        bus1.set_onBoarding(2);
//        bus1.setCurFuel(-50);
//        System.out.println("주유량 : "+bus1.getCurFuel());
//        bus1.changeState();
//        bus1.setCurFuel(10);
//        System.out.println(bus1.state);
//        System.out.println("주유량 = "+ bus1.getCurFuel());
//        bus1.changeState();
//        bus1.set_onBoarding(45);
//        bus1.set_onBoarding(5);
//        bus1.setCurFuel(-55);
//        System.out.println("주유량 = "+ bus1.getCurFuel());
//        bus1.changeState();
//        System.out.println(bus1.state);
//

        Taxi taxi1 = new Taxi(1);

        System.out.println(taxi1.getCurFuel());
        System.out.println(taxi1.getState());
        taxi1.set_onBoarding(2);
        taxi1.acrossLoad("서울역", 2);
        taxi1.setCurFuel(-80);
        taxi1.set_onBoarding(5);
        taxi1.set_onBoarding(3);
        taxi1.acrossLoad("구로디지털단지역", 12);
        taxi1.setCurFuel(-20);
    }
}
