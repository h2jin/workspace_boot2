package tenco.com.test_20;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Bgm {

	public Bgm() {

		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/bgm.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);

			// 소리 설정
			FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

			// 볼륨 조절
			control.setValue(-40.0f);
			clip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
