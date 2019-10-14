package com.example.android.cricketscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This app keeps lets a user track the score of two teams in a cricket match (handy during a gully match!)
 */
public class MainActivity extends AppCompatActivity {

    int runsTeamA = 0;
    int runsTeamB = 0;
    int wicketsTeamA = 0;
    int wicketsTeamB = 0;
    int oversTeamA = 0;
    int oversTeamB = 0;
    int ballsTeamA = 0;
    int ballsTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Saves app state during a configuration change
     *
     * @param outState state of the app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("runsTeamA", runsTeamA);
        outState.putInt("runsTeamB", runsTeamB);
        outState.putInt("wicketsTeamA", wicketsTeamA);
        outState.putInt("wicketsTeamB", wicketsTeamB);
        outState.putInt("oversTeamA", oversTeamA);
        outState.putInt("oversTeamB", oversTeamB);
        outState.putInt("ballsTeamA", ballsTeamA);
        outState.putInt("ballsTeamB", ballsTeamB);
    }

    /**
     * Restores app state after a configuration change
     *
     * @param savedInstanceState saved app state
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        runsTeamA = savedInstanceState.getInt("runsTeamA");
        runsTeamB = savedInstanceState.getInt("runsTeamB");
        wicketsTeamA = savedInstanceState.getInt("wicketsTeamA");
        wicketsTeamB = savedInstanceState.getInt("wicketsTeamB");
        oversTeamA = savedInstanceState.getInt("oversTeamA");
        oversTeamB = savedInstanceState.getInt("oversTeamB");
        ballsTeamA = savedInstanceState.getInt("ballsTeamA");
        ballsTeamB = savedInstanceState.getInt("ballsTeamB");
        displayScore(runsTeamA, wicketsTeamA, R.id.team_a_score);
        displayScore(runsTeamB, wicketsTeamB, R.id.team_b_score);
        displayOvers(oversTeamA, ballsTeamA, R.id.team_a_overs);
        displayOvers(oversTeamB, ballsTeamB, R.id.team_b_overs);
    }

    /**
     * Displays the score for a team
     *
     * @param runs        runs of the team
     * @param wickets     wickets of the team
     * @param scoreViewId TextView id of the team whose score needs to be updated
     */
    public void displayScore(int runs, int wickets, int scoreViewId) {
        TextView scoreView = findViewById(scoreViewId);
        scoreView.setText(getString(R.string.score, runs, wickets));
    }

    /**
     * Displays the overs for a team
     *
     * @param overs       overs of the team
     * @param balls       balls of the team
     * @param oversViewId TextView id of the team whose overs need to be updated
     */
    public void displayOvers(int overs, int balls, int oversViewId) {
        TextView oversView = findViewById(oversViewId);
        oversView.setText(getString(R.string.overs, overs, balls));
    }

    /**
     * Checks if the over is up (balls == 6)
     *
     * @param balls balls bowled in the current over
     * @return true if over is up, false otherwise
     */
    public boolean isOverUp(int balls) {
        return balls == 6;
    }

    /**
     * Adds a ball to the current over
     *
     * @param oversViewId TextView id of the team whose balls/overs need to be updated
     */
    public void addBall(int oversViewId) {
        switch (oversViewId) {
            case R.id.team_a_overs:
                ballsTeamA++;
                if (isOverUp(ballsTeamA)) {
                    oversTeamA++;
                    ballsTeamA = 0;
                    displayOvers(oversTeamA, ballsTeamA, R.id.team_a_overs);
                } else {
                    displayOvers(oversTeamA, ballsTeamA, R.id.team_a_overs);
                }
                break;
            case R.id.team_b_overs:
                ballsTeamB++;
                if (isOverUp(ballsTeamB)) {
                    oversTeamB++;
                    ballsTeamB = 0;
                    displayOvers(oversTeamB, ballsTeamB, R.id.team_b_overs);
                } else {
                    displayOvers(oversTeamB, ballsTeamB, R.id.team_b_overs);
                }
                break;
        }
    }

    /**
     * Adds a dot ball to the team's score
     *
     * @param view view of the team whose score needs to be updated
     */
    public void addDot(View view) {
        switch (view.getId()) {
            case R.id.team_a_dot:
                addBall(R.id.team_a_overs);
                break;
            case R.id.team_b_dot:
                addBall(R.id.team_b_overs);
                break;
        }
    }

    /**
     * Adds 1 run to the team's score
     *
     * @param view view of the team whose score needs to be updated
     */
    public void addOne(View view) {
        switch (view.getId()) {
            case R.id.team_a_one_run:
                runsTeamA++;
                addBall(R.id.team_a_overs);
                displayScore(runsTeamA, wicketsTeamA, R.id.team_a_score);
                break;
            case R.id.team_b_one_run:
                runsTeamB++;
                addBall(R.id.team_b_overs);
                displayScore(runsTeamB, wicketsTeamB, R.id.team_b_score);
                break;
        }
    }

    /**
     * Adds 2 runs to the team's score
     *
     * @param view view of the team whose score needs to be updated
     */
    public void addTwo(View view) {
        switch (view.getId()) {
            case R.id.team_a_two_runs:
                runsTeamA += 2;
                addBall(R.id.team_a_overs);
                displayScore(runsTeamA, wicketsTeamA, R.id.team_a_score);
                break;
            case R.id.team_b_two_runs:
                runsTeamB += 2;
                addBall(R.id.team_b_overs);
                displayScore(runsTeamB, wicketsTeamB, R.id.team_b_score);
                break;
        }
    }

    /**
     * Adds 3 runs to the team's score
     *
     * @param view view of the team whose score needs to be updated
     */
    public void addThree(View view) {
        switch (view.getId()) {
            case R.id.team_a_three_runs:
                runsTeamA += 3;
                addBall(R.id.team_a_overs);
                displayScore(runsTeamA, wicketsTeamA, R.id.team_a_score);
                break;
            case R.id.team_b_three_runs:
                runsTeamB += 3;
                addBall(R.id.team_b_overs);
                displayScore(runsTeamB, wicketsTeamB, R.id.team_b_score);
                break;
        }
    }

    /**
     * Adds 4 runs to the team's score
     *
     * @param view view of the team whose score needs to be updated
     */
    public void addFour(View view) {
        switch (view.getId()) {
            case R.id.team_a_four_runs:
                runsTeamA += 4;
                addBall(R.id.team_a_overs);
                displayScore(runsTeamA, wicketsTeamA, R.id.team_a_score);
                break;
            case R.id.team_b_four_runs:
                runsTeamB += 4;
                addBall(R.id.team_b_overs);
                displayScore(runsTeamB, wicketsTeamB, R.id.team_b_score);
                break;
        }
    }

    /**
     * Adds 6 runs to the team's score
     *
     * @param view view of the team whose score needs to be updated
     */
    public void addSix(View view) {
        switch (view.getId()) {
            case R.id.team_a_six_runs:
                runsTeamA += 6;
                addBall(R.id.team_a_overs);
                displayScore(runsTeamA, wicketsTeamA, R.id.team_a_score);
                break;
            case R.id.team_b_six_runs:
                runsTeamB += 6;
                addBall(R.id.team_b_overs);
                displayScore(runsTeamB, wicketsTeamB, R.id.team_b_score);
                break;
        }
    }

    /**
     * Adds a wicket to the team's score
     *
     * @param view view of the team whose score needs to be updated
     */
    public void addWicket(View view) {
        switch (view.getId()) {
            case R.id.team_a_wicket:
                wicketsTeamA++;
                addBall(R.id.team_a_overs);
                displayScore(runsTeamA, wicketsTeamA, R.id.team_a_score);
                break;
            case R.id.team_b_wicket:
                wicketsTeamB++;
                addBall(R.id.team_b_overs);
                displayScore(runsTeamB, wicketsTeamB, R.id.team_b_score);
                break;
        }
    }

    /**
     * Resets the score of both teams
     *
     * @param view reset button view
     */
    public void reset(View view) {
        runsTeamA = 0;
        runsTeamB = 0;
        wicketsTeamA = 0;
        wicketsTeamB = 0;
        oversTeamA = 0;
        oversTeamB = 0;
        ballsTeamA = 0;
        ballsTeamB = 0;
        displayScore(0, 0, R.id.team_a_score);
        displayScore(0, 0, R.id.team_b_score);
        displayOvers(0, 0, R.id.team_a_overs);
        displayOvers(0, 0, R.id.team_b_overs);
    }
}