import lejos.hardware.motor.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.lcd.*;
import java.util.Random;

class golfbane{
	public static void main(String[] args){
		try{
			Brick brick = BrickFinder.getDefault();
			Port s1 = brick.getPort("S1");
			Port s2 = brick.getPort("S2");

			EV3 ev3 = (EV3) BrickFinder.getLocal();
			TextLCD lcd = ev3.getTextLCD();
			Keys keys = ev3.getKeys();

			SampleProvider touchSensor1 = new EV3TouchSensor(s1);
			float[] touchSamples = new float[touchSensor1.sampleSize() * 2];
			SampleProvider touchSensor2 = new EV3TouchSensor(s2);

			Random rand = new Random();

			Motor.A.setSpeed(450);
			Motor.B.setSpeed(450);

			lcd.drawString("Trykk for starte", 0, 1);
			keys.waitForAnyPress();

			//Drive forward
			Motor.A.forward();
			Motor.B.forward();

			while(true){
				if (touchSamples != null && touchSamples.length > 0){
					touchSensor1.fetchSample(touchSamples, 0);
					touchSensor2.fetchSample(touchSamples, touchSensor1.sampleSize());
					if (touchSamples[0] > 0 || touchSamples[touchSensor1.sampleSize()] > 0){
						//Back
						Motor.A.backward();
						Motor.B.backward();
						Thread.sleep(750);

						//Turn
						Motor.A.stop();
						Motor.B.stop();


						int i = (rand.nextInt(270) + 90);
						Motor.A.rotate(i);
						while (Motor.A.isMoving()) Thread.yield();

						//Forward
						Motor.A.forward();
						Motor.B.forward();
				}
			}
		}
	}
	catch(Exception e){
	System.out.println("Feil: "+ e +"");
	}
	}
}
