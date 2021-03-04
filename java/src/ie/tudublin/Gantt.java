package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> tasks = new ArrayList<Task>();

	float border;
	float label;
	float left;

	int whichTask = -1;
	boolean isEnd = false;

	public void loadTasks()
	{
		Table table = loadTable("tasks.csv", "header");

		for (TableRow row:table.rows()) {
			Task t = new Task(row);
			tasks.add(t);
		}
	}

	public void printTasks()
	{
		for (Task t:tasks) {
			println(t);
		}
	}

	public void drawLines() {

		textAlign(CENTER, CENTER);

		for (int i = 1 ; i <= 30 ; i++) {
			float x = map(i, 0, 30, left, width - border);
			stroke(255);
			line(x, border, x, height - border);
			fill(255);
			text(i, x, border / 2);
		}
	}

	public void drawLabels() {
		textAlign(LEFT, CENTER);
		int i = 1;

		for (Task t:tasks) {
			fill(255);
			float y = map(i, 0, 10, border, height - border);
			text(t.getTask(), border, y);
			i++;
		}
	}

	public void displayTasks() {
		colorMode(HSB);
		noStroke();
		float c = 255 / 9;
		int i = 0;

		for (Task t:tasks) {
			float x = map(t.getStart(), 0, 30, left, width - border);
			float y = map(i, 0, 10, border, height - border);
			float w = map((t.getEnd() - t.getStart()), 0, 30, 0, width - border - left);
			float h = (height - (border * 2)) / 12;
			fill(i * c, 255, 255);
			rect(x, y + (border * 0.75f), w, h);
			i++;
		}
	}
	
	public void mousePressed()
	{
		// Find which task it is by finding y
		// Find the start and end of task using x1 and x2
	}

	public void mouseDragged()
	{
		// Map the mouse onto the screen
	}
	
	public void settings()
	{
		size(800, 600);
	}

	public void setup() 
	{
		border = width * 0.05f;
		label = width * 0.12f;
		left = border + label;

		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
		drawLines();
		drawLabels();
		displayTasks();
	}
}
