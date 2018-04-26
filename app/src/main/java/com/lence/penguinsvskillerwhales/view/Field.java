//package com.lence.penguinsvskillerwhales.view;
//
//import android.graphics.Point;
//import android.util.Log;
//
//import com.lence.penguinsvskillerwhales.model.Organism;
//import com.lence.penguinsvskillerwhales.model.Penguin;
//import com.lence.penguinsvskillerwhales.utils.Constants;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class Field {
//
//    private int height;
//    private int width;
//    private List<Organism> mLists;
//
//    public Field(int height, int width) {
//        this.height = height;
//        this.width = width;
//        mLists = Collections.synchronizedList(new ArrayList<Organism>(height * width));
//    }
//
//    public void set(int x, int y, Organism organism) {
//        mLists.set(y * width + x, organism);
//    }
//
//    public void set(Point thisPoint, Organism organism) {
//        mLists.set(thisPoint.y * width + thisPoint.x, organism);
//    }
//
//    public Organism get(int x, int y) {
//        Log.e("get", x+" "+y);
//        return mLists.get(y * width + x);
//    }
//
//    public Organism get(Point thisPoint) {
//        return mLists.get(thisPoint.y * width + thisPoint.x);
//    }
//
//    public boolean isInField(int x, int y) {
//        return ((x >= 0) && (x < width) && (y >= 0) && (y < height));
//    }
//
//    public void add(Organism organism) {
//        mLists.add(organism);
//    }
//
//    public static Point getPointByDirection(int x,int y, int direction) {
//        switch (direction) {
//            case Constants.left: {
//                return new Point(x-1,y);
//            }
//            case Constants.right: {
//                return new Point(x+1,y);
//            }
//            case Constants.up: {
//                return new Point(x,y-1);
//            }
//            case Constants.down: {
//                return new Point(x,y+1);
//            }
//            case Constants.left_up: {
//                return new Point(x-1,y-1);
//            }
//            case Constants.right_up: {
//                return new Point(x+1,y-1);
//            }
//            case Constants.left_down: {
//                return new Point(x-1,y+1);
//            }
//            case Constants.right_down: {
//                return new Point(x+1,y+1);
//            }
//        }
//        return new Point(x,y);
//    }
//
//    public int size() {
//        return width * height;
//    }
//
//
//    /*public List<Organism> getmLists() {
//        return mLists;
//    }
//
//    public void setmLists(List<Organism> mLists) {
//        this.mLists = mLists;
//    }*/
//}
