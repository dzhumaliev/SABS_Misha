package kg.sunrise.sabs.ui.cash_box.tab.fragments.custom;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.text.DecimalFormat;

public class MyValueFormatter extends ValueFormatter
{
    private final DecimalFormat mFormat;
    private String suffix;

    public MyValueFormatter(String suffix) {
        mFormat = new DecimalFormat("###,###,###,##0.0");
        this.suffix = suffix;
    }
    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value) + suffix;
    }
}
