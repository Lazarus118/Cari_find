package co.aulatech.cari_find;

class DataObject {

    private int mImage1;
    private String mText1;
    private String mText2;

    DataObject (int image1, String text1, String text2){
        mImage1 = image1;
        mText1 = text1;
        mText2 = text2;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public int getmImage1() {
        return mImage1;
    }

    public void setmImage1(int mImage1) {
        this.mImage1 = mImage1;
    }
}