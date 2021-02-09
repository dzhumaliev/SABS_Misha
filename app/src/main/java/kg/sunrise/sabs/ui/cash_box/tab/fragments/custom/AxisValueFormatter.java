package kg.sunrise.sabs.ui.cash_box.tab.fragments.custom;
import android.annotation.SuppressLint;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import kg.sunrise.sabs.utils.ContainsKt;

public class AxisValueFormatter extends ValueFormatter{
    String t = go();
    String nd2 = parseDateFormat(t);
    String nd3 = parseDateFormat(nd2);
    String nd4 = parseDateFormat(nd3);
    String nd5 = parseDateFormat(nd4);
    String nd6 = parseDateFormat(nd5);
    String nd7 = parseDateFormat(nd6);
    private final String[] mWeek = new String[]{
       t, nd2, nd3, nd4, nd5, nd6, nd7};

    private final BarLineChartBase<?> chart;

    public AxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }
    @Override
    public String getFormattedValue(float value) {
        int days = (int) value;
        String weekDay = mWeek[days % mWeek.length];
        chart.getVisibleXRange();
        return weekDay;
    }
    public static String today() {
        String dateString = new SimpleDateFormat("EE", Locale.getDefault()).format(new Date());
        return dateString;
    }
    public static String parseDateFormat(String inputDate) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatInput = new SimpleDateFormat("EE");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatOutput = new SimpleDateFormat("EE");
        Date date = null;
        try {
            date = formatInput.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = Objects.requireNonNull(date).getTime()+1000*60*60*24;
        String dateString = formatOutput.format(diff);
        return dateString;
    }
    public static String go() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatInput = new SimpleDateFormat("MM/dd/yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatOutput = new SimpleDateFormat("EE");
        Date date = null;
        try {
            date = formatInput.parse(ContainsKt.getW());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = Objects.requireNonNull(date).getTime();
        String dateString = formatOutput.format(diff);
        return dateString;
    }
}
