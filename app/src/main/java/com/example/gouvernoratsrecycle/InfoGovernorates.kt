package com.example.gouvernoratsrecycle

class InfoGovernorates(val name: String, val imageId: Int) {
    constructor(name: String, imageId: Int, additionalProperty: String) : this(name, imageId) {
    }
}