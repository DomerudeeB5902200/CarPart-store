package com.car.part.project;

import java.util.Collection;
import java.util.stream.Stream;
import com.car.part.project.entity.*;
import com.car.part.project.repository.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CarTypeRepository carTypeRepository, CarGenerationRepository carGenerationRepository,
			CarLabelRepository carLabelRepository, CarPartRepository carPartRepository, BillRepository billRepository) {
		return args -> {
			Stream.of("รถจักรยานยนต์", "รถยนต์", "รถยนต์สิบล้อ").forEach(carTypeName -> {
				CarType carType = new CarType();
				carType.setCarTypeName(carTypeName);
				carType = carTypeRepository.save(carType);
				CarType carTypeSave = carTypeRepository.findById(carType.getCarTypeId().longValue());
				if (carTypeName.equals("รถจักรยานยนต์")) {
					Stream.of("รถจักรยานยนต์ BMW", "รถจักรยานยนต์ CFMOTO", "รถจักรยานยนต์ Ducati", "รถจักรยานยนต์ GPX", "รถจักรยานยนต์ Honda *", "รถจักรยานยนต์ Kawasaki", "รถจักรยานยนต์ Suzuki", "รถจักรยานยนต์ Vespa", "รถจักรยานยนต์ Yamaha")
							.forEach(carLabelName -> {
								CarLabel carLabel = new CarLabel();
								carLabel.setCarLabelName(carLabelName);
								carLabel = carLabelRepository.save(carLabel);
								CarLabel carLabelSave = carLabelRepository
										.findById(carLabel.getCarLabelId().longValue());
								if (carLabelName.equals("รถจักรยานยนต์ BMW")) {
									Stream.of("BMW s1000R").forEach(carGenerationName -> {
										CarGeneration carGeneration = new CarGeneration();
										carGeneration.setCarType(carTypeSave);
										carGeneration.setCarLabel(carLabelSave);
										carGeneration.setGenerationCarName(carGenerationName);
										carGenerationRepository.save(carGeneration);
									});
								}
								if (carLabelName.equals("รถจักรยานยนต์ CFMOTO")) {
									Stream.of("CFMOTO 250NK").forEach(carGenerationName -> {
										CarGeneration carGeneration = new CarGeneration();
										carGeneration.setCarType(carTypeSave);
										carGeneration.setCarLabel(carLabelSave);
										carGeneration.setGenerationCarName(carGenerationName);
										carGenerationRepository.save(carGeneration);
									});
								}
								if (carLabelName.equals("รถจักรยานยนต์ Ducati")) {
									Stream.of("Ducati Panigale V4", "Ducati Panigale V45", "Ducati Scrambler Classic")
											.forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถจักรยานยนต์ GPX")) {
									Stream.of("GPX RAZER 220", "GPX Demon 150 GR Luxury", "GPX Wonwan 110 CC.")
											.forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถจักรยานยนต์ Honda *")) {
									Stream.of("Honda * Monkey", "Honda * PCX150", "Honda * Wave 125i",
											"Honda * Dream Super Cub").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถจักรยานยนต์ Kawasaki")) {
									Stream.of("Kawasaki ER6N", "Kawasaki NINJA 250SL", "Kawasaki ER6N")
											.forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถจักรยานยนต์ Suzuki")) {
									Stream.of("Suzuki Smash 115", "Suzuki GSX-R 150").forEach(carGenerationName -> {
										CarGeneration carGeneration = new CarGeneration();
										carGeneration.setCarType(carTypeSave);
										carGeneration.setCarLabel(carLabelSave);
										carGeneration.setGenerationCarName(carGenerationName);
										carGenerationRepository.save(carGeneration);
									});
								}
								if (carLabelName.equals("รถจักรยานยนต์ Vespa")) {
									Stream.of("Vespa LX 125 3Vie", "Vespa Sprint 125", "Vespa GTS 150")
											.forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถจักรยานยนต์ Yamaha")) {
									Stream.of("Yamaha Filano", "Yamaha Finn 115", "Yamaha M-SLAZ", "Yamaha NMAX",
											"Yamaha GT125").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
							});
				}

				if (carTypeName.equals("รถยนต์")) {
					Stream.of("รถยนต์ TOYOTA", "รถยนต์ ISUZU", "รถยนต์ Honda", "รถยนต์ Mitsubishi", "รถยนต์ Ford", "รถยนต์ Nissan", "รถยนต์ Mazda", "รถยนต์ Suzuki", "รถยนต์ MG",
							"รถยนต์ Benz", "รถยนต์ BMW", "รถยนต์ Volvo").forEach(carLabelName -> {
								CarLabel carLabel = new CarLabel();
								carLabel.setCarLabelName(carLabelName);
								carLabel = carLabelRepository.save(carLabel);
								CarLabel carLabelSave = carLabelRepository
										.findById(carLabel.getCarLabelId().longValue());
								if (carLabelName.equals("รถยนต์ TOYOTA")) {
									Stream.of("TOYOTA HILUX REVO", "TOYOTA YARIS", "TOYOTA COROLLA ALTIS",
											"TOYOTA YARIS ATIV", "TOYOTA FORTUNER").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ ISUZU")) {
									Stream.of("ISUZU MU-X", "ISUZU Spark", "ISUZU 2-Door", "ISUZU 4-Door")
											.forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ Honda")) {
									Stream.of("Honda Accord G9", "Honda Accord G8", "Honda Accord G7", "Honda CR-V",
											"Honda HR-V ", "Honda Jazz", "Honda Civic", "Honda City")
											.forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ Mitsubishi")) {
									Stream.of("Mitsubishi 3000 GTO", "Mitsubishi Aerobody", "Mitsubishi Airtrek",
											"Mitsubishi Astron", "Mitsubishi Attrage", "Mitsubishi Champ",
											"Mitsubishi Colt", "Mitsubishi Cyclone", "Mitsubishi Delica",
											"Mitsubishi Eclipse", "Mitsubishi FTO", "Mitsubishi G-WAGON",
											"Mitsubishi Galant").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ Ford")) {
									Stream.of("Ford Ford", "Ford Ranger for work", "Ford Ranger", "Ford Raptor",
											"Ford Mustang").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ Nissan")) {
									Stream.of("Nissan Almera", "Nissan X-Trail", "Nissan Note", "Nissan Terra",
											"Nissan March").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ Mazda")) {
									Stream.of("Mazda 2", "Mazda 2 Sky Active", "Mazda 3", "Mazda CX")
											.forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ Suzuki")) {
									Stream.of("Suzuki Swift", "Suzuki Carry", "Suzuki Celerio", "Suzuki Ciaz",
											"Suzuki Ertiga").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ MG")) {
									Stream.of("MG ZS", "MG Extender Giant", "MG Extender Double", "MG HS", "MG V80",
											"MG 3").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ Benz")) {
									Stream.of("Benz GLC-class", "Benz C-class", "Benz GLE-class", "Benz GLK-class",
											"Benz M-class").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ BMW")) {
									Stream.of("BMW X5 (E53)", "BMW I8", "BMW ROADSTER", "BMW M5 (E39)", "BMW SERIES")
											.forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
								if (carLabelName.equals("รถยนต์ Volvo")) {
									Stream.of("Volvo XC40", "Volvo S90", "Volvo SUV XC90", "Volvo XC60", "Volvo V60",
											"Volvo XC40").forEach(carGenerationName -> {
												CarGeneration carGeneration = new CarGeneration();
												carGeneration.setCarType(carTypeSave);
												carGeneration.setCarLabel(carLabelSave);
												carGeneration.setGenerationCarName(carGenerationName);
												carGenerationRepository.save(carGeneration);
											});
								}
							});

				}
				if (carTypeName.equals("รถยนต์สิบล้อ")) {
					Stream.of("สิบล้อ ISUZU", "สิบล้อ HINO", "สิบล้อ SCANIA", "สิบล้อ FOTON", "สิบล้อ Volvo").forEach(carLabelName -> {
						CarLabel carLabel = new CarLabel();
						carLabel.setCarLabelName(carLabelName);
						carLabel = carLabelRepository.save(carLabel);
						CarLabel carLabelSave = carLabelRepository.findById(carLabel.getCarLabelId().longValue());
						if (carLabelName.equals("สิบล้อ ISUZU")) {
							Stream.of("ISUZU FRR MAX TORQUE").forEach(carGenerationName -> {
								CarGeneration carGeneration = new CarGeneration();
								carGeneration.setCarType(carTypeSave);
								carGeneration.setCarLabel(carLabelSave);
								carGeneration.setGenerationCarName(carGenerationName);
								carGenerationRepository.save(carGeneration);
							});
						}
						if (carLabelName.equals("สิบล้อ HINO")) {
							Stream.of("HINO  FC Model 500 Dominator").forEach(carGenerationName -> {
								CarGeneration carGeneration = new CarGeneration();
								carGeneration.setCarType(carTypeSave);
								carGeneration.setCarLabel(carLabelSave);
								carGeneration.setGenerationCarName(carGenerationName);
								carGenerationRepository.save(carGeneration);
							});
						}
						if (carLabelName.equals("สิบล้อ SCANIA")) {
							Stream.of("SCANIA").forEach(carGenerationName -> {
								CarGeneration carGeneration = new CarGeneration();
								carGeneration.setCarType(carTypeSave);
								carGeneration.setCarLabel(carLabelSave);
								carGeneration.setGenerationCarName(carGenerationName);
								carGenerationRepository.save(carGeneration);
							});
						}
						if (carLabelName.equals("สิบล้อ Volvo")) {
							Stream.of("Volvo FH16").forEach(carGenerationName -> {
								CarGeneration carGeneration = new CarGeneration();
								carGeneration.setCarType(carTypeSave);
								carGeneration.setCarLabel(carLabelSave);
								carGeneration.setGenerationCarName(carGenerationName);
								carGenerationRepository.save(carGeneration);
							});
						}
						if (carLabelName.equals("สิบล้อ FOTON")) {
							Stream.of("FOTON Commando 11 Tons").forEach(carGenerationName -> {
								CarGeneration carGeneration = new CarGeneration();
								carGeneration.setCarType(carTypeSave);
								carGeneration.setCarLabel(carLabelSave);
								carGeneration.setGenerationCarName(carGenerationName);
								carGenerationRepository.save(carGeneration);
							});
						}
					});

				}

			});

			/* CarType carType = carTypeRepository.findById(1);
			// CarLabel carLabel = carLabelRepository.findById(2);
			// CarGeneration carGeneration = carGenerationRepository.findById(3);
			
			 * CarGeneration carGeneration = new CarGeneration();
			 * carGeneration.setCarLabel(carLabel); carGeneration.setCarType(carType);
			 * carGeneration.setGenerationCarName("Honda move");
			 * carGenerationRepository.save(carGeneration);
			 */

		};
	};

}