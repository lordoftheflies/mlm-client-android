package flavon.dd.com.topflavonandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference to progress bar.
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        // Reference to web-view.
        myWebView = (WebView) findViewById(R.id.webView);

        // Setup for use Polymer
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        // Locate base url of the frontend from the webapp.
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.loadUrl(getResources().getString(R.string.base_url));

        // Set our webclient
        myWebView.setWebViewClient(new AppWebViewClients(this.progressBar));
    }

    public class AppWebViewClients extends WebViewClient {
        private ProgressBar progressBar;

        public AppWebViewClients(ProgressBar progressBar) {
            this.progressBar=progressBar;
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;

//            // Handle local URLs.
//            if (Uri.parse(url).getHost().length() == 0) {
//                return false;
//            }
//
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            view.getContext().startActivity(intent);
//            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }
}
