package si.cit.clothingorigin.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import si.cit.clothingorigin.R;
import timber.log.Timber;

/**
 *
 */

public class FontView extends android.support.v7.widget.AppCompatTextView {
    public static final String FONT_ASSET_LATO_REGULAR = "LatoRegular.ttf";
    public static final String FONT_ASSET_LATO_ITALIC = "LatoItalic.ttf";
    public static final String FONT_ASSET_LATO_LIGHT = "LatoLight.ttf";
    public static final String FONT_ASSET_LATO_LIGHT_ITALIC = "LatoLightItalic.ttf";
    public static final String FONT_ASSET_LATO_THIN = "LatoThin.ttf";
    public static final String FONT_ASSET_LATO_THIN_ITALIC = "LatoThinItalic.ttf";
    public static final String FONT_ASSET_LATO_MEDIUM = "LatoMedium.ttf";
    public static final String FONT_ASSET_LATO_MEDIUM_ITALIC = "LatoMediumItalic.ttf";
    public static final String FONT_ASSET_LATO_BOLD = "LatoBold.ttf";
    public static final String FONT_ASSET_LATO_BOLD_ITALIC = "LatoBoldItalic.ttf";
    public static final String FONT_ASSET_LATO_BLACK = "LatoBlack.ttf";
    public static final String FONT_ASSET_LATO_BLACK_ITALIC = "LatoBlackItalic.ttf";
    public static final String FONT_ASSET_LATO_HEAVY = "LatoHeavy.ttf";
    public static final String FONT_ASSET_LATO_HEAVY_ITALIC = "LatoHeavyItalic.ttf";


    public FontView(Context context) {
        super(context);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public FontView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.FontView);
        //Get selected flag font
        int fontChoice = a.getInt(R.styleable.FontView_customFont,1);
        String customFont = "";
        switch (fontChoice){
            case 1:
                customFont = FONT_ASSET_LATO_REGULAR;
                break;
            case 11:
                customFont = FONT_ASSET_LATO_ITALIC;
                break;
            case 2:
                customFont = FONT_ASSET_LATO_LIGHT;
                break;
            case 21:
                customFont = FONT_ASSET_LATO_LIGHT_ITALIC;
                break;
            case 3:
                customFont = FONT_ASSET_LATO_THIN;
                break;
            case 31:
                customFont = FONT_ASSET_LATO_THIN_ITALIC;
                break;
            case 4:
                customFont = FONT_ASSET_LATO_BOLD;
                break;
            case 41:
                customFont = FONT_ASSET_LATO_BOLD_ITALIC;
                break;
            case 5:
                customFont = FONT_ASSET_LATO_BLACK;
                break;
            case 51:
                customFont = FONT_ASSET_LATO_BLACK_ITALIC;
                break;
            case 6:
                customFont = FONT_ASSET_LATO_HEAVY;
                break;
            case 61:
                customFont = FONT_ASSET_LATO_HEAVY_ITALIC;
                break;
            case 7:
                customFont = FONT_ASSET_LATO_MEDIUM;
                break;
            case 71:
                customFont = FONT_ASSET_LATO_MEDIUM_ITALIC;
                break;
        }
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface typeface = null;
        if(asset!=null && !asset.equals("")) {
            try {
                typeface = Typeface.createFromAsset(ctx.getAssets(), asset);
            } catch (Exception e) {
                Timber.e("Unable to load typeface: " + e.getMessage());
                return false;
            }
        }
        if(typeface!=null)setTypeface(typeface);
        return true;
    }
}
