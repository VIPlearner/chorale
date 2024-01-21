package com.viplearner.chorale.model

data class SongDetails(
    val songId: String,
    val song: String = "For the Lord God omnipotent reigneth\nThe kingdom of this world and of his Christ\nAnd so on and so forth\nAnother line in the lyrics\nAnd yet another",
    val solfa: String = "d: r: m: d | l: t:-:- || d: r: m:\nd: r: m: d | l: t:-:- || d: r: m:\nd: r: m: d | l: t:-:- || d: r: m:\nd: r: m: d | l: t:-:- || d: r: m:\nd: r: m: d | l: t:-:- || d: r: m:"
)