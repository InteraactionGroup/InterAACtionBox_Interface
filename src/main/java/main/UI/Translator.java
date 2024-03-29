package main.UI;

public interface Translator {

    interface LanguageChangeListener {

        void languageChanged();

    }

    String translate(String key);

    String translate(String... keys);

    void registerLanguageChangeListener(LanguageChangeListener listener);

    void notifyLanguageChanged();

    void changeLanguage(String language);

}
