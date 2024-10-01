package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15).followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0)).forward(30).turn(Math.toRadians(90)).forward(30).turn(Math.toRadians(90)).forward(30).turn(Math.toRadians(90)).forward(30).turn(Math.toRadians(90)).build());

        Image img = null;
        try { img = ImageIO.read(new File("https://github.com/NoahBres/MeepMeep/blob/master/src/main/resources/background/season-2024-intothedeep/field-2024-juice-black.png")); }
        catch (IOException e) {}

        meepMeep.setBackground(img);
    }
}
