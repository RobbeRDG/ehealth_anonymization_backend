package be.kul.useraccess.Utils.Converters;

import be.kul.useraccess.Utils.Enums.SupportedScriptLanguage;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, SupportedScriptLanguage> {
    @Override
    public SupportedScriptLanguage convert(String source) {
        return SupportedScriptLanguage.valueOf(source.toUpperCase());
    }
}
