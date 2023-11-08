
package net.xerxesbeat.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import net.xerxesbeat.cord.Cord;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Map;

public class Table
{

    private ConcurrentLinkedQueue<Table> array =
            new ConcurrentLinkedQueue<>();

    private ConcurrentHashMap<String, Table> map =
            new ConcurrentHashMap<>();

    private Cord<JsonElement> cord = new Cord<>();

    public Table ()
    {
    }

    public Table ( JsonElement element )
    {
        cord.set(element);
    }

    public Table get ( String key )
    {
        String [] keys = key.split("\\.");

        Table current = this;
        for ( String k : keys )
        {
            current = current.map.get(k);
            if ( current == null )
            {
                // Handle the case where key does not exist in the hierarchy
                return null;
            }
        }

        return current;
    }

    public int length ()
    {
        return array.size();
    }

    public Table get ( int index )
    {
        if ( index >= 0 && index < array.size() )
        {
            // Get DataStructure at the specified index in the queue
            Table [] arrayCopy = array.toArray(new Table [0]);
            return arrayCopy[index];
        } else
        {
            // Handle the case where the index is out of bounds
            return null;
        }
    }

    public void set ( int index, Cord<JsonElement> jsonElementCord )
    {
        if ( index >= 0 && index <= array.size() )
        {
            // Insert Cord<JsonElement> at the specified index in the queue
            Table [] arrayCopy = array.toArray(new Table [0]);
            for ( int i = arrayCopy.length; i > index; i-- )
            {
                arrayCopy[i] = arrayCopy[i - 1];
            }
            arrayCopy[index].cord.set(jsonElementCord.get());
            array.clear();
            for ( Table element : arrayCopy )
            {
                array.add(element);
            }
        } else
        {
            // TODO
            // Handle the case where the index is out of bounds
        }
    }

    public void set ( String key, Cord<JsonElement> jsonElementCord )
    {
        String [] keys = key.split("\\.");

        Table current = this;
        for ( int i = 0; i < keys.length - 1; i++ )
        {
            String k = keys[i];
            current.map.putIfAbsent( k, new Table ( null ) );
            current = current.map.get(k);
        }

        // Set Cord<JsonElement> at the specified key in the hierarchy
        String lastKey = keys[keys.length - 1];
        if ( current.map.contains( lastKey ) )
        {
            Cord<JsonElement> output = current.map.get( lastKey ).cord;
            output.set( jsonElementCord.get() );
        }
        else
            current.map.put( lastKey, new Table ( jsonElementCord.get() ) );
    }

    public void insert ( int index, Cord<JsonElement> jsonElementCord )
    {
        if ( index >= 0 && index <= array.size() )
        {
            Table [] arrayCopy = array.toArray(new Table [0]);
            array.clear();

            for ( int i = 0; i < arrayCopy.length; i++ )
            {
                if ( i == index )
                {
                    array.add( new Table ( jsonElementCord.get() ) );
                }
                array.add(arrayCopy[i]);
            }

            if ( index == arrayCopy.length )
            {
                array.add( new Table ( jsonElementCord.get() ) );
            }
        } else
        {
            // Handle the case where the index is out of bounds
            // You can throw an exception, log an error, or handle it in any way
            // suitable for your application.
        }
    }

    public void append ( Cord<JsonElement> jsonElementCord )
    {
        array.add( new Table ( jsonElementCord.get() ) );
    }

    public JsonObject buildJsonObject ()
    {
        JsonObject jsonObject = new JsonObject();

        // Convert the queue to a JSON array
        JsonArray queueArray = new JsonArray();
        for ( Table element : array )
        {
            queueArray.add(element.buildJsonObject());
        }
        jsonObject.add("queue", queueArray);

        // Build the namespace structure and add it to the JSON object
        for ( Map.Entry<String, Table> entry : map.entrySet() )
        {
            jsonObject.add(entry.getKey(), entry.getValue().buildJsonObject());
        }

        // Add Cord<JsonElement> content to the JSON object
        jsonObject.add("cord", cord.get());

        return jsonObject;
    }

    protected JsonElement getElement ()
    {
        return cord.get();
    }
}