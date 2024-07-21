package com.example.ParkingLot;

import com.example.ParkingLot.commands.Command;
import com.example.ParkingLot.commands.CommandRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ParkingLotApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ParkingLotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
//		String readLine = sc.nextLine();
//		System.out.println(readLine);

		while(true){
			System.out.println("Enter command : ");
			String input = sc.nextLine();
			if(input.equals("EXIT")) break;

			Command command = CommandRegistry.getInstance().getCommand(input);
			command.execute(input);
		}
	}
}
