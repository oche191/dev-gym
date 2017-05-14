package com.oche.gym.battleships;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author alex.chekanskyi
 */
public class Battleship {
    Map<Point, Ship> pointToShip = new HashMap<>();

    public static void main(String[] args) {
        Battleship bs = new Battleship();
        bs.realTest();
        bs.printStatus();
    }

    public void realTest() {
        addShipToBoard("b2","b2");
        addShipToBoard("e10","f12");
        addShipToBoard("p2","r2");

        //destroy b2
        makeShot("b2");
        //wound e10 f12
        makeShot("f10");
        makeShot("e11");
        //destroy p2 r2
        makeShot("p2");
        makeShot("q2");
        makeShot("r2");
    }

    public void addShipToBoard(String point1, String point2) {
        Set<Point> shipBody = getShipBody(point1, point2);
        Ship s = new Ship(shipBody);
        for(Point p : shipBody) {
            pointToShip.put(p, s);
        }
    }

    public void makeShot(String point) {
        Point p = getPointFromCoords(point);
        if(pointToShip.containsKey(p)) {
            pointToShip.get(p).takeDamage(p);
        }
    }

    public void printStatus() {
        int destroyed = 0;
        int wounded = 0;
        Set<Ship> ships = new HashSet<>();
        for (Ship s : pointToShip.values()) {
            ships.add(s);
        }
        for (Ship ship : ships) {
            if(ship.isDestroyed()) {
                destroyed++;
            }
            if(ship.isWounded()) {
                wounded ++;
            }
        }

        System.out.println("DESTROYED: " + destroyed);
        System.out.println("WOUNDED: " + wounded);
    }

    public Set<Point> getShipBody(String p1, String p2) {
        Point firstPoint = getPointFromCoords(p1);
        Point secondPoint = getPointFromCoords(p2);
        Set<Point> result = new HashSet<>();
        //one point ship
        if(firstPoint.equals(secondPoint)) {
            result.add(firstPoint);
            return result;
        }

        //vertical case
        if(firstPoint.x == secondPoint.x) {
            int start;
            int end;
            if(firstPoint.y < secondPoint.y) {
                start = firstPoint.y;
                end = secondPoint.y;
            } else {
                start = secondPoint.y;
                end = firstPoint.y;
            }
            for(;start<=end;start++) {
                result.add(new Point(firstPoint.x, start));
            }
            return result;
        }
        //horizontal case
        if(firstPoint.y == secondPoint.y) {
            int start;
            int end;
            if(firstPoint.x < secondPoint.x) {
                start = firstPoint.x;
                end = secondPoint.x;
            } else {
                start = secondPoint.x;
                end = firstPoint.x;
            }
            for(;start<=end;start++) {
                result.add(new Point(start, firstPoint.y));
            }
            return result;
        }
        //normal case
        for(int i=firstPoint.x;i<=secondPoint.x;i++) {
            for(int j=firstPoint.y;j<=secondPoint.y;j++) {
                result.add(new Point(i, j));
            }
        }
        return result;
    }

    public Point getPointFromCoords(String p) {
        p = p.trim().toLowerCase();
        int x = getLetterIndex(p.charAt(0));
        int y = Integer.parseInt(p.substring(1));
        return new Point(x, y);
    }

    private int getLetterIndex(char c) {
        int temp = (int)c;
        int temp_integer = 96; //for lower case

        return (temp-temp_integer);
    }


    class Ship {
        private Set<Point> state = new HashSet<>();
        private int initStateSize;

        Ship(Set<Point> state) {
            this.state = state;
            this.initStateSize = state.size();
        }

        public void takeDamage(Point point) {
            state.remove(point);
        }

        public boolean isDestroyed() {
            return state.size() == 0;
        }

        public boolean isWounded() {
            if(!isDestroyed() && state.size() < initStateSize) {
                return true;
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Ship ship = (Ship) o;

            if (initStateSize != ship.initStateSize) return false;
            return state != null ? state.equals(ship.state) : ship.state == null;
        }

        @Override
        public int hashCode() {
            int result = state != null ? state.hashCode() : 0;
            result = 31 * result + initStateSize;
            return result;
        }
    }

    class Point {
        int x,y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
