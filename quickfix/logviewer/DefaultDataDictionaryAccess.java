package quickfix.logviewer;

import quickfix.DataDictionary;

/**
 * Author: Sol
 * DateTime: 2014/02/25 01:28
 */
public class DefaultDataDictionaryAccess implements DataDictionaryAccess
{
    public void setDataDictionary(DataDictionary dataDictionary)
    {
        this.dataDictionary = dataDictionary;
    }

    private DataDictionary dataDictionary;

    public DefaultDataDictionaryAccess(DataDictionary dataDictionary)
    {
        this.dataDictionary = dataDictionary;
    }

    @Override
    public DataDictionary getDataDictionary()
    {
        return dataDictionary;
    }
}
