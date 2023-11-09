package model;

import java.util.Random;

public class Horse {
   private String name;
   private int position;
   private boolean finished;

   public Horse(String name) {
      this.name = name;
      this.position = 0;
      this.finished = false;
   }

   public String getName() {
      return name;
   }

   public int getPosition() {
      return position;
   }

   public boolean hasFinished() {
      return finished;
   }

   public void setFinished(boolean finished) {
      this.finished = finished;
   }

   public void move() {
      Random random = new Random();
      int moveDistance = random.nextInt(3) + 1; // 1에서 5까지의 랜덤한 이동
      position += moveDistance;
   }
}// horse5