package serviceImpl;

import com.lpq.mail.service.IanalysisService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建人：肖易安
 * 创建时间：  2020/5/19
 * 注释：null
 **/
public class AnalysisServiceImpl implements IanalysisService {
    private String context ;

    public AnalysisServiceImpl(String context) {
        this.context = context;
    }

    public AnalysisServiceImpl() {
    }

    @Override
    public String from() {
        String pattern = "(From: ).*(<)(.*)(?=>)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(context);
        if (m.find( )) {
            return m.group(3);
        } else {
            return null ;
        }
    }

    @Override
    public String to() {
        String pattern = "(To: ).*(<)(.*)(?=>)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(context);
        if (m.find( )) {
            return m.group(3);
        } else {
            return null ;
        }
    }

    @Override
    public Timestamp date() throws ParseException {
        String pattern = "(Date: )(.*?)\\n";
        Pattern r = Pattern.compile(pattern) ;
        Matcher m = r.matcher(context) ;
        if (m.find()) {
            String time = m.group(2);
            time = time.replaceAll("," , "") ;
            time = time.substring(0, 24) ;
            SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf1.parse(time) ;
            time = sdf2.format(date) ;
            Timestamp ts ;
            try {
                ts = Timestamp.valueOf(time);
                return ts ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return null;
        }
        return null ;
    }

    @Override
    public String subjectCharset() {
        String pattern = "(Subject: ).*(=\\?)(.*)(\\?)" ;
        Pattern r = Pattern.compile(pattern) ;
        Matcher m = r.matcher(context) ;
        String[] subject = null ;
        if (m.find( )) {
            subject = m.group(3).split("\\?");
            return subject[0] ;
        } else {
            return null;
        }
    }

    @Override
    public String subjectText() {
        String pattern = "(Subject: ).*(=\\?)(.*)(\\?)" ;
        Pattern r = Pattern.compile(pattern) ;
        Matcher m = r.matcher(context) ;
        String[] subject = null ;
        if (m.find( )) {
            subject = m.group(3).split("\\?");
            return subject[2] ;
        } else {
            return null;
        }
    }

    @Override
    public String bodyCharset() {
        String text = init(context) ;
        String pattern = "(?=Content-Type: text/plain).*(charset=)(.*)(Content-Transfer-Encoding: base64)" ;
        Pattern r = Pattern.compile(pattern) ;
        Matcher m = r.matcher(text) ;
        if (m.find( )) {
           return m.group(2);
        } else {
            return null ;
        }
    }

    @Override
    public String bodyText() {
        String text = init(context) ;
        String pattern = "Content-Transfer-Encoding: base64(.*?)------" ;
        Pattern r = Pattern.compile(pattern) ;
        Matcher m = r.matcher(text) ;
        if (m.find( )) {
            return m.group(1);
        } else {
            return null ;
        }
    }

    private String init(String line){
        String result =line.replaceAll("\t|\n", "");
        return result ;
    }
}
