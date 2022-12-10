import java.util.ArrayList;
import java.util.Scanner;



public class Day09 implements AoCTask {
	
	int n = 8; // input size
	
	String[] input = new String[n];
	
	@Override
	public void readInput(Scanner scan) {
		for(int i = 0; i < n; i++) {
			input[i] = scan.nextLine();
		}
	}


	@Override
	public String task1() {
		Knot[] rope = new Knot[2];

		for (int i = rope.length - 1; i >=0 ; i--) {
			rope[i] = new Knot(0,0);
			if(i < rope.length - 1) rope[i].tail = rope[i+1];
		}
		System.out.println(rope[0].tail);

		Step [] arr = new Step[input.length];
		for (int i = 0; i < input.length; i++) {
			String[] str = input[i].split(" ");
			arr[i] = new Step(str[0].charAt(0), Integer.valueOf(str[1]));
		};

		for (Step el : arr) {
			int length = (el.direction == 'R' | el.direction == 'U') ? 0 + el.length : 0 - el.length;
			if (el.direction == 'R'  | el.direction == 'L') {
				for(int i = 1; i < rope.length; i++) {
					rope[i-1].move(length, 0);
				}
			}
			if (el.direction == 'U'  | el.direction == 'D') {
				for(int i = 1; i < rope.length; i++) {
					rope[i-1].move(0, length);
				}			}
		};


		return "" + rope[rope.length - 1].visited.size();
	}

	@Override
	public String task2() {
		Knot[] rope = new Knot[10];

		for (int i = rope.length - 1; i >=0 ; i--) {
			rope[i] = new Knot(0,0);
			if(i < rope.length - 1) rope[i].tail = rope[i+1];
		}
		System.out.println(rope[0].tail);

		Step [] arr = new Step[input.length];
		for (int i = 0; i < input.length; i++) {
			String[] str = input[i].split(" ");
			arr[i] = new Step(str[0].charAt(0), Integer.valueOf(str[1]));
		};

		for (Step el : arr) {
			int length = (el.direction == 'R' | el.direction == 'U') ? 0 + el.length : 0 - el.length;
			if (el.direction == 'R'  | el.direction == 'L') {
				for(int i = 1; i < rope.length; i++) {
					rope[i-1].move(length, 0);
				}
			}
			if (el.direction == 'U'  | el.direction == 'D') {
				for(int i = 1; i < rope.length; i++) {
					rope[i-1].move(0, length);
				}			}
		};


		return "" + rope[rope.length - 1].visited.size();
	}

}
class Step {
	char direction;
	int length;
	Step (char str, int l) {
		this.direction = str;
		this.length = l;
	}
	public int getLength() {
		return length;
	}
	public char getDirection() {
		return direction;
	}

}
class Position {
	int x;
	int y;
	Position(int x, int y){
		this.x =x;
		this.y = y;
	}
}
class PositionSet {
	ArrayList<Position> storage = new ArrayList<Position>();
	PositionSet(){};
	PositionSet(Position pos){storage.add(pos); };

	public void add(Position pos) {
		//System.out.println("received" + pos.x + "/" +pos.y);
		boolean contains = false;

		for (Position el : storage) {
			//System.out.println("el" + el.x + "/" +el.y);
			if (el.x == pos.x && el.y == pos.y) {
				//System.out.println("contains");

				contains = true;
				break;
		}}
		if (!contains) {
			//System.out.println("added");
			storage.add(pos);
	}
}

public String toString(){
	String s = "";
	for(Position el : storage){
		s = s +" \\(" + el.x +"/"+el.y+"\\) ";
	}
	return "["+s+"]";
}
	public int size() {
		return storage.size();
	}
}


class Knot {
	int x = 0;
	int y = 0;
	Knot tail;

	PositionSet visited = new PositionSet(new Position(x, y));
	
	Knot(){};
	Knot(int x, int y){
		this.x = x;
		this.y = y;
		visited.add(new Position(x, y));
	};


	void move(int x, int y){ // ok
			this.visited.add(new Position(this.x, this.y));

		

			int i = 0;

			while (i != Math.abs(x)) {
				this.x = (x > 0 ? this.x+1 : this.x-1);
				i++;
				Position p = new Position(this.x, this.y);
				this.visited.add(p);
				if(tail != null) tail.updateTail(this);
			}
			int j = 0;
			
			while (j != Math.abs(y)) {
				this.y = (y > 0 ? this.y+1 : this.y-1);
				j++;
				Position p = new Position(this.x, this.y);
				this.visited.add(p); // task 1
				if(tail != null) tail.updateTail(this); // task 1
			}
		}

	
	void updateTail(Knot head) { // same col
			if(head.x == x) {
				if (head.y - 1 > y) {
					y = head.y -1;
					visited.add(new Position(x, y));
				}
				if (head.y + 1 < y) {
					y = head.y+1;
					visited.add(new Position(x, y));
	
				}
			}
			else if(head.y == y) { // same row
				if (head.x -1 > x) {
					x = head.x -1;
					visited.add(new Position(x, y));
	
				}
				if (head.x + 1< x) {
					x = head.x+1;
					visited.add(new Position(x, y));
	
				}
			}
			else { // diagonal move
	
			if(!((head.x == x-1 && head.y == y+1) |			
				(head.x == x-1 && head.y == y-1)|	
				(head.x == x+1 && head.y == y-1)|		
				(head.x == x+1 && head.y == y+1))){if (head.y > y && head.x > x){
					x = x + 1;
					y = y + 1;
	
				}
				if (head.y < y && head.x > x){
					x = x+1;
					y=y-1;
				}
				if (head.y > y && head.x < x){
					x= x-1;
					y = y+1;
				}
				if (head.y < y && head.x < x){
					x = x-1;
					y = y-1;
				}}		
		
			
	
					
					visited.add(new Position(x, y));
					if (tail != null) tail.updateTail(this);
				
				}
		}
	}
	

