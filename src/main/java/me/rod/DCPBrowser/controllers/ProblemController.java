package me.rod.DCPBrowser.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.net.http.HttpResponse;

@Controller
public class ProblemController {
    @Value("${spring.application.name}")
    String appName;

    private String sendGet(String payloadUrl) throws Exception {

        URL obj = new URL(payloadUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + payloadUrl);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        //print result
        System.out.println(response);
        return response.toString();
    }

    @GetMapping("/view/all/problems")
    public String index(Model model) throws Exception {
        String problemsIndexPage = "https://raw.githubusercontent.com/vineetjohn/daily-coding-problem/master/README.md";
        String problemsResponse = sendGet(problemsIndexPage);

        String[] problemsArray = problemsResponse.split("---");

        int problemsArrayLength = problemsArray.length;
        for (int i = 0; i < problemsArrayLength; i++) {
            ;
        }

        String[] singleQuestion = problemsArray[1].split("\n");
        String questionBuilder = "";
        int questionLength = singleQuestion.length;
        for (int j = 0; j < questionLength; j++) {
            questionBuilder += (singleQuestion[j] + "\n");
            System.out.println(singleQuestion[j]);
            System.out.println("^^^^^^^^^^^^");
        }

        model.addAttribute("problemSpace", questionBuilder);
        return "viewAllProblems";
    }
}
