package uk.co.chrisjenx.calligraphy;

import android.graphics.Typeface;
import android.text.TextUtils;

/**
 * Created by chris on 20/12/2013
 * Project: Calligraphy
 */
public class CalligraphyConfig {

    private static CalligraphyConfig mInstance;

    /**
     * Init the Calligraphy Config file. Each time you call this you set a new default. Of course setting this multiple
     * times during runtime could have undesired effects.
     *
     * @param defaultFontAssetPath a path to a font file in the assets folder, e.g. "fonts/roboto-light.ttf",
     *                             passing null will default to the device font-family.
     */
    public static void initDefault(String defaultFontAssetPath) {
        if (CalligraphyUtils.isRegexFontAssetPath(defaultFontAssetPath)) {
            String[] generatedFontPaths = CalligraphyUtils.generateFontAssetPaths(defaultFontAssetPath);
            mInstance = new CalligraphyConfig(generatedFontPaths[0], generatedFontPaths[1],
                    generatedFontPaths[2], generatedFontPaths[3]);
        } else {
            mInstance = new CalligraphyConfig(defaultFontAssetPath);
        }
    }

    /**
     * Init only the custom attribute to lookup.
     *
     * @param defaultAttributeId the custom attribute to look for.
     * @see #initDefault(String, int)
     */
    public static void initDefault(int defaultAttributeId) {
        mInstance = new CalligraphyConfig(defaultAttributeId);
    }

    /**
     * Define the default font and the custom attribute to lookup globally.
     *
     * @param defaultFontAssetPath path to a font file in the assets folder, e.g. "fonts/Roboto-light.ttf",
     * @param defaultAttributeId   the custom attribute to look for.
     * @see #initDefault(String)
     * @see #initDefault(int)
     */
    public static void initDefault(String defaultFontAssetPath, int defaultAttributeId) {
        if (CalligraphyUtils.isRegexFontAssetPath(defaultFontAssetPath)) {
            String[] generatedFontPaths = CalligraphyUtils.generateFontAssetPaths(defaultFontAssetPath);
            mInstance = new CalligraphyConfig(generatedFontPaths[0], generatedFontPaths[1],
                    generatedFontPaths[2], generatedFontPaths[3], defaultAttributeId);
        } else {
            mInstance = new CalligraphyConfig(defaultFontAssetPath, defaultAttributeId);
        }
    }

    /**
     * Define the defaults fonts and custom attribute to lookup globally. All paths will
     * reference files in the asserts folder, e.g. "assets/fonts/Roboto-light.ttf"
     *
     * @param defaultFontAssetPath path to the font to be used for regular text
     * @param defaultFontBoldAssetPath path to the font to be used for bold text
     * @param defaultFontItalicAssetPath path to the font to be used for italic text
     * @param defaultFontBoldItalicAssetPath path to the font to be used for bold italic text
     * @param defaultAttributeId the custom attribute to look for
     */
    public static void initDefault(String defaultFontAssetPath, String defaultFontBoldAssetPath,
                                   String defaultFontItalicAssetPath, String defaultFontBoldItalicAssetPath,
                                   int defaultAttributeId) {
        mInstance = new CalligraphyConfig(defaultFontAssetPath, defaultFontBoldAssetPath,
                defaultFontItalicAssetPath, defaultFontBoldItalicAssetPath, defaultAttributeId);
    }

    static CalligraphyConfig get() {
        if (mInstance == null)
            mInstance = new CalligraphyConfig();
        return mInstance;
    }


    private final String mFontPath;
    private final String mFontPathBold;
    private final String mFontPathItalic;
    private final String mFontPathBoldItalic;
    private final boolean mIsFontSet;
    private final int mAttrId;

    private CalligraphyConfig() {
        this(null, null, null, null, -1);
    }

    private CalligraphyConfig(int attrId) {
        this(null, null, null, null, attrId);
    }

    private CalligraphyConfig(String defaultFontAssetPath) {
        this(defaultFontAssetPath, null, null, null, -1);
    }

    private CalligraphyConfig(String defaultFontAssetPath, int defaultAttributeId) {
        this(defaultFontAssetPath, null, null, null, defaultAttributeId);
    }

    private CalligraphyConfig(String defaultFontAssetPath, String defaultFontBoldAssetPath,
                              String defaultFontItalicAssetPath, String defaultFontBoldItalicAssetPath) {
        this(defaultFontAssetPath, defaultFontBoldAssetPath, defaultFontItalicAssetPath, defaultFontBoldItalicAssetPath, -1);
    }

    private CalligraphyConfig(String defaultFontAssetPath, String defaultFontBoldAssetPath,
                              String defaultFontItalicAssetPath, String defaultFontBoldItalicAssetPath,
                              int attrId) {
        this.mFontPath = defaultFontAssetPath;
        this.mFontPathBold = defaultFontBoldAssetPath;
        this.mFontPathItalic = defaultFontItalicAssetPath;
        this.mFontPathBoldItalic = defaultFontBoldItalicAssetPath;
        mIsFontSet = !TextUtils.isEmpty(defaultFontAssetPath);
        mAttrId = attrId != -1 ? attrId : -1;
    }

    /**
     * @return mFontPath for text views might be null
     */
    String getStyledFontPath(int style) {
        switch (style) {
            case Typeface.NORMAL:
                return mFontPath;
            case Typeface.BOLD:
                if (!TextUtils.isEmpty(mFontPathBold)) {
                    return mFontPathBold;
                } else {
                    return mFontPath;
                }
            case Typeface.ITALIC:
                if (!TextUtils.isEmpty(mFontPathItalic)) {
                    return mFontPathItalic;
                } else {
                    return mFontPath;
                }
            case Typeface.BOLD_ITALIC:
                if (!TextUtils.isEmpty(mFontPathBoldItalic)) {
                    return mFontPathBoldItalic;
                } else {
                    return mFontPath;
                }
            default:
                return mFontPath;
        }
    }

    /**
     * @return mFontPath for text views might be null
     */
    String getFontPath() {
        return mFontPath;
    }

    /**
     * @return mFontPathBold for text views, might be null
     */
    String getBoldFontPath() {
        return mFontPathBold;
    }

    /**
     * @return mFontPathItalic for text views, might be null
     */
    String getItalicFontPath() {
        return mFontPathItalic;
    }

    /**
     * @return mFontPathBoldItalic for text views, might be null
     */
    String getBoldItalicFontPath() {
        return mFontPathBoldItalic;
    }

    /**
     * @return true if set, false if null|empty
     */
    boolean isFontSet() {
        return mIsFontSet;
    }

    /**
     * @return the custom attrId to look for, -1 if not set.
     */
    public int getAttrId() {
        return mAttrId;
    }
}
