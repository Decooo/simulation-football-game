package com.jakub.footballgame;

import com.jakub.footballgame.config.FxmlView;
import com.jakub.footballgame.config.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FootballgameApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private StageManager stageManager;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init(){
		springContext = springBootApplicationContext();
	}

	@Override
	public void start(Stage primaryStage){
		stageManager = springContext.getBean(StageManager.class, primaryStage);
		displayInitialScene();
	}

	@Override
	public void stop(){
		springContext.close();
	}

	protected void displayInitialScene(){
		stageManager.switchScene(FxmlView.MAINVIEW);
	}

	private ConfigurableApplicationContext springBootApplicationContext(){
		SpringApplicationBuilder builder = new SpringApplicationBuilder(FootballgameApplication.class);
		String[] args = getParameters().getRaw().toArray(new String[0]);
		return builder.run(args);
	}

}
