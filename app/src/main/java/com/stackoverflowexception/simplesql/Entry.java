package com.stackoverflowexception.simplesql;

/**
 * Created by adrian on 10/03/15.
 */
public class Entry {

        String _key;
        String _value;

        public Entry(String name, String value){
            this._key = name;
            this._value = value;
        }

        public String getKey(){
            return this._key;
        }

        public String getValue(){
            return this._value;
        }

    }
