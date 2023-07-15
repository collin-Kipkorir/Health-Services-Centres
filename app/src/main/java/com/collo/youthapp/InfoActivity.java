package com.collo.youthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class InfoActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        // Set the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.theme));
        }
        // Find the WebView in the layout
        webView = findViewById(R.id.webview);

        // Load the HTML content
        String htmlContent =
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "  <title>Summary: Sexual and Reproductive Health and Rights (SRHR) and HIV</title>\n" +
                        "  <style>\n" +
                        "    body {\n" +
                        "      font-family: Arial, sans-serif;\n" +
                        "      margin: 20px;\n" +
                        "    }\n" +
                        "    h1 {\n" +
                        "      color: #333;\n" +
                        "      font-size: 18px;\n" +
                        "      margin-bottom: 10px;\n" +
                        "    }\n" +
                        "    p {\n" +
                        "      color: #666;\n" +
                        "      font-size: 16px;\n" +
                        "      line-height: 1.5;\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <h1>Sexual and Reproductive Health and Rights (SRHR)</h1>\n" +
                        "  <p>\n" +
                        "    SRHR encompasses the right of individuals to have control over their sexual and reproductive lives, free from discrimination, coercion, and violence.\n" +
                        "    It includes access to comprehensive sexual education, family planning services, maternal and child health care, safe and legal abortion, prevention and treatment of sexually transmitted infections (STIs), and prevention and response to gender-based violence.\n" +
                        "    SRHR aims to ensure that individuals can make informed decisions about their sexual and reproductive health, exercise their reproductive rights, and have access to quality healthcare services.\n" +
                        "  </p>\n" +
                        "\n" +
                        "  <h1>HIV (Human Immunodeficiency Virus)</h1>\n" +
                        "  <p>\n" +
                        "    HIV is a virus that attacks the immune system, weakening the body's ability to fight off infections and diseases.\n" +
                        "    It is primarily transmitted through unprotected sexual intercourse, sharing contaminated needles, and from an infected mother to her child during childbirth or breastfeeding.\n" +
                        "    HIV can progress to AIDS (Acquired Immunodeficiency Syndrome), a condition characterized by severe immune system damage and increased vulnerability to opportunistic infections and certain cancers.\n" +
                        "    Effective prevention measures include practicing safe sex, using condoms, accessing HIV testing and counseling, implementing harm reduction strategies for drug use, and preventing mother-to-child transmission through antiretroviral therapy.\n" +
                        "    People living with HIV can lead healthy lives with early diagnosis, access to antiretroviral treatment, and ongoing medical care.\n" +
                        "  </p>\n" +
                        "\n" +
                        "  <p>\n" +
                        "    The intersection of SRHR and HIV recognizes the importance of addressing sexual health and reproductive rights in the context of HIV prevention, treatment, care, and support.\n" +
                        "    Promoting comprehensive SRHR services alongside HIV prevention strategies can contribute to improved overall health outcomes and well-being for individuals and communities.\n" +
                        "  </p>\n" +
                        "</body>\n" +
                        "</html>\n";
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
    }
}