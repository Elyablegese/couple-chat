import com.iia.couplechat.data.model.Country
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

const val countriesData = """
[{
	"country_code": 1876,
	"short_name": "JM",
	"name": "Jamaica",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/jm.png"
}, {
	"country_code": 1869,
	"short_name": "KN",
	"name": "Saint Kitts & Nevis",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/kn.png"
}, {
	"country_code": 1868,
	"short_name": "TT",
	"name": "Trinidad & Tobago",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/tt.png"
}, {
	"country_code": 1784,
	"short_name": "VC",
	"name": "Saint Vincent & the Grenadines",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/vc.png"
}, {
	"country_code": 1767,
	"short_name": "DM",
	"name": "Dominica",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/dm.png"
}, {
	"country_code": 1758,
	"short_name": "LC",
	"name": "Saint Lucia",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/lc.png"
}, {
	"country_code": 1721,
	"short_name": "SX",
	"name": "Sint Maarten",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/sx.png"
}, {
	"country_code": 1684,
	"short_name": "AS",
	"name": "American Samoa",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/as.png"
}, {
	"country_code": 1671,
	"short_name": "GU",
	"name": "Guam",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/gu.png"
}, {
	"country_code": 1670,
	"short_name": "MP",
	"name": "Northern Mariana Islands",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/mp.png"
}, {
	"country_code": 1664,
	"short_name": "MS",
	"name": "Montserrat",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/ms.png"
}, {
	"country_code": 1649,
	"short_name": "TC",
	"name": "Turks & Caicos Islands",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/tc.png"
}, {
	"country_code": 1473,
	"short_name": "GD",
	"name": "Grenada",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/gd.png"
}, {
	"country_code": 1441,
	"short_name": "BM",
	"name": "Bermuda",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/bm.png"
}, {
	"country_code": 1345,
	"short_name": "KY",
	"name": "Cayman Islands",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/ky.png"
}, {
	"country_code": 1340,
	"short_name": "VI",
	"name": "US Virgin Islands",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/vi.png"
}, {
	"country_code": 1284,
	"short_name": "VG",
	"name": "British Virgin Islands",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/vg.png"
}, {
	"country_code": 1268,
	"short_name": "AG",
	"name": "Antigua & Barbuda",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/ag.png"
}, {
	"country_code": 1264,
	"short_name": "AI",
	"name": "Anguilla",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/ai.png"
}, {
	"country_code": 1246,
	"short_name": "BB",
	"name": "Barbados",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/bb.png"
}, {
	"country_code": 1242,
	"short_name": "BS",
	"name": "Bahamas",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/bs.png"
}, {
	"country_code": 998,
	"short_name": "UZ",
	"name": "Uzbekistan",
	"format": "XX XXXXXXX",
	"url": "https://flagcdn.com/24x18/uz.png"
}, {
	"country_code": 996,
	"short_name": "KG",
	"name": "Kyrgyzstan",
	"format": "XXX XXXXXX",
	"url": "https://flagcdn.com/24x18/kg.png"
}, {
	"country_code": 995,
	"short_name": "GE",
	"name": "Georgia",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/ge.png"
}, {
	"country_code": 994,
	"short_name": "AZ",
	"name": "Azerbaijan",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/az.png"
}, {
	"country_code": 993,
	"short_name": "TM",
	"name": "Turkmenistan",
	"format": "XX XXXXXX",
	"url": "https://flagcdn.com/24x18/tm.png"
}, {
	"country_code": 992,
	"short_name": "TJ",
	"name": "Tajikistan",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/tj.png"
}, {
	"country_code": 977,
	"short_name": "NP",
	"name": "Nepal",
	"format": "XX XXXX XXXX",
	"url": "https://flagcdn.com/24x18/np.png"
}, {
	"country_code": 976,
	"short_name": "MN",
	"name": "Mongolia",
	"format": "XX XX XXXX",
	"url": "https://flagcdn.com/24x18/mn.png"
}, {
	"country_code": 975,
	"short_name": "BT",
	"name": "Bhutan",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/bt.png"
}, {
	"country_code": 974,
	"short_name": "QA",
	"name": "Qatar",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/qa.png"
}, {
	"country_code": 973,
	"short_name": "BH",
	"name": "Bahrain",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/bh.png"
}, {
	"country_code": 972,
	"short_name": "IL",
	"name": "Israel",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/il.png"
}, {
	"country_code": 971,
	"short_name": "AE",
	"name": "United Arab Emirates",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ae.png"
}, {
	"country_code": 970,
	"short_name": "PS",
	"name": "Palestine",
	"format": "XXX XX XXXX",
	"url": "https://flagcdn.com/24x18/ps.png"
}, {
	"country_code": 968,
	"short_name": "OM",
	"name": "Oman",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/om.png"
}, {
	"country_code": 967,
	"short_name": "YE",
	"name": "Yemen",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/ye.png"
}, {
	"country_code": 966,
	"short_name": "SA",
	"name": "Saudi Arabia",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/sa.png"
}, {
	"country_code": 965,
	"short_name": "KW",
	"name": "Kuwait",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/kw.png"
}, {
	"country_code": 964,
	"short_name": "IQ",
	"name": "Iraq",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/iq.png"
}, {
	"country_code": 963,
	"short_name": "SY",
	"name": "Syria",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/sy.png"
}, {
	"country_code": 962,
	"short_name": "JO",
	"name": "Jordan",
	"format": "X XXXX XXXX",
	"url": "https://flagcdn.com/24x18/jo.png"
}, {
	"country_code": 961,
	"short_name": "LB",
	"name": "Lebanon",
	"format": "",
	"url": "https://flagcdn.com/24x18/lb.png"
}, {
	"country_code": 960,
	"short_name": "MV",
	"name": "Maldives",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/mv.png"
}, {
	"country_code": 886,
	"short_name": "TW",
	"name": "Taiwan",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/tw.png"
}, {
	"country_code": 880,
	"short_name": "BD",
	"name": "Bangladesh",
	"format": "",
	"url": "https://flagcdn.com/24x18/bd.png"
}, {
	"country_code": 856,
	"short_name": "LA",
	"name": "Laos",
	"format": "XX XX XXX XXX",
	"url": "https://flagcdn.com/24x18/la.png"
}, {
	"country_code": 855,
	"short_name": "KH",
	"name": "Cambodia",
	"format": "",
	"url": "https://flagcdn.com/24x18/kh.png"
}, {
	"country_code": 853,
	"short_name": "MO",
	"name": "Macau",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/mo.png"
}, {
	"country_code": 852,
	"short_name": "HK",
	"name": "Hong Kong",
	"format": "X XXX XXXX",
	"url": "https://flagcdn.com/24x18/hk.png"
}, {
	"country_code": 850,
	"short_name": "KP",
	"name": "North Korea",
	"format": "",
	"url": "https://flagcdn.com/24x18/kp.png"
}, {
	"country_code": 692,
	"short_name": "MH",
	"name": "Marshall Islands",
	"format": "",
	"url": "https://flagcdn.com/24x18/mh.png"
}, {
	"country_code": 691,
	"short_name": "FM",
	"name": "Micronesia",
	"format": "",
	"url": "https://flagcdn.com/24x18/fm.png"
}, {
	"country_code": 690,
	"short_name": "TK",
	"name": "Tokelau",
	"format": "",
	"url": "https://flagcdn.com/24x18/tk.png"
}, {
	"country_code": 689,
	"short_name": "PF",
	"name": "French Polynesia",
	"format": "",
	"url": "https://flagcdn.com/24x18/pf.png"
}, {
	"country_code": 688,
	"short_name": "TV",
	"name": "Tuvalu",
	"format": "",
	"url": "https://flagcdn.com/24x18/tv.png"
}, {
	"country_code": 687,
	"short_name": "NC",
	"name": "New Caledonia",
	"format": "",
	"url": "https://flagcdn.com/24x18/nc.png"
}, {
	"country_code": 686,
	"short_name": "KI",
	"name": "Kiribati",
	"format": "",
	"url": "https://flagcdn.com/24x18/ki.png"
}, {
	"country_code": 685,
	"short_name": "WS",
	"name": "Samoa",
	"format": "",
	"url": "https://flagcdn.com/24x18/ws.png"
}, {
	"country_code": 683,
	"short_name": "NU",
	"name": "Niue",
	"format": "",
	"url": "https://flagcdn.com/24x18/nu.png"
}, {
	"country_code": 682,
	"short_name": "CK",
	"name": "Cook Islands",
	"format": "",
	"url": "https://flagcdn.com/24x18/ck.png"
}, {
	"country_code": 681,
	"short_name": "WF",
	"name": "Wallis & Futuna",
	"format": "",
	"url": "https://flagcdn.com/24x18/wf.png"
}, {
	"country_code": 680,
	"short_name": "PW",
	"name": "Palau",
	"format": "",
	"url": "https://flagcdn.com/24x18/pw.png"
}, {
	"country_code": 679,
	"short_name": "FJ",
	"name": "Fiji",
	"format": "",
	"url": "https://flagcdn.com/24x18/fj.png"
}, {
	"country_code": 678,
	"short_name": "VU",
	"name": "Vanuatu",
	"format": "",
	"url": "https://flagcdn.com/24x18/vu.png"
}, {
	"country_code": 677,
	"short_name": "SB",
	"name": "Solomon Islands",
	"format": "",
	"url": "https://flagcdn.com/24x18/sb.png"
}, {
	"country_code": 676,
	"short_name": "TO",
	"name": "Tonga",
	"format": "",
	"url": "https://flagcdn.com/24x18/to.png"
}, {
	"country_code": 675,
	"short_name": "PG",
	"name": "Papua New Guinea",
	"format": "",
	"url": "https://flagcdn.com/24x18/pg.png"
}, {
	"country_code": 674,
	"short_name": "NR",
	"name": "Nauru",
	"format": "",
	"url": "https://flagcdn.com/24x18/nr.png"
}, {
	"country_code": 673,
	"short_name": "BN",
	"name": "Brunei Darussalam",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/bn.png"
}, {
	"country_code": 672,
	"short_name": "NF",
	"name": "Norfolk Island",
	"format": "",
	"url": "https://flagcdn.com/24x18/nf.png"
}, {
	"country_code": 670,
	"short_name": "TL",
	"name": "Timor-Leste",
	"format": "",
	"url": "https://flagcdn.com/24x18/tl.png"
}, {
	"country_code": 599,
	"short_name": "BQ",
	"name": "Bonaire, Sint Eustatius & Saba",
	"format": "",
	"url": "https://flagcdn.com/24x18/bq.png"
}, {
	"country_code": 599,
	"short_name": "CW",
	"name": "Curaçao",
	"format": "",
	"url": "https://flagcdn.com/24x18/cw.png"
}, {
	"country_code": 598,
	"short_name": "UY",
	"name": "Uruguay",
	"format": "X XXX XXXX",
	"url": "https://flagcdn.com/24x18/uy.png"
}, {
	"country_code": 597,
	"short_name": "SR",
	"name": "Suriname",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/sr.png"
}, {
	"country_code": 596,
	"short_name": "MQ",
	"name": "Martinique",
	"format": "",
	"url": "https://flagcdn.com/24x18/mq.png"
}, {
	"country_code": 595,
	"short_name": "PY",
	"name": "Paraguay",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/py.png"
}, {
	"country_code": 594,
	"short_name": "GF",
	"name": "French Guiana",
	"format": "",
	"url": "https://flagcdn.com/24x18/gf.png"
}, {
	"country_code": 593,
	"short_name": "EC",
	"name": "Ecuador",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ec.png"
}, {
	"country_code": 592,
	"short_name": "GY",
	"name": "Guyana",
	"format": "",
	"url": "https://flagcdn.com/24x18/gy.png"
}, {
	"country_code": 591,
	"short_name": "BO",
	"name": "Bolivia",
	"format": "X XXX XXXX",
	"url": "https://flagcdn.com/24x18/bo.png"
}, {
	"country_code": 590,
	"short_name": "GP",
	"name": "Guadeloupe",
	"format": "XXX XX XX XX",
	"url": "https://flagcdn.com/24x18/gp.png"
}, {
	"country_code": 509,
	"short_name": "HT",
	"name": "Haiti",
	"format": "",
	"url": "https://flagcdn.com/24x18/ht.png"
}, {
	"country_code": 508,
	"short_name": "PM",
	"name": "Saint Pierre & Miquelon",
	"format": "",
	"url": "https://flagcdn.com/24x18/pm.png"
}, {
	"country_code": 507,
	"short_name": "PA",
	"name": "Panama",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/pa.png"
}, {
	"country_code": 506,
	"short_name": "CR",
	"name": "Costa Rica",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/cr.png"
}, {
	"country_code": 505,
	"short_name": "NI",
	"name": "Nicaragua",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/ni.png"
}, {
	"country_code": 504,
	"short_name": "HN",
	"name": "Honduras",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/hn.png"
}, {
	"country_code": 503,
	"short_name": "SV",
	"name": "El Salvador",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/sv.png"
}, {
	"country_code": 502,
	"short_name": "GT",
	"name": "Guatemala",
	"format": "X XXX XXXX",
	"url": "https://flagcdn.com/24x18/gt.png"
}, {
	"country_code": 501,
	"short_name": "BZ",
	"name": "Belize",
	"format": "",
	"url": "https://flagcdn.com/24x18/bz.png"
}, {
	"country_code": 500,
	"short_name": "FK",
	"name": "Falkland Islands",
	"format": "",
	"url": "https://flagcdn.com/24x18/fk.png"
}, {
	"country_code": 423,
	"short_name": "LI",
	"name": "Liechtenstein",
	"format": "",
	"url": "https://flagcdn.com/24x18/li.png"
}, {
	"country_code": 421,
	"short_name": "SK",
	"name": "Slovakia",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/sk.png"
}, {
	"country_code": 420,
	"short_name": "CZ",
	"name": "Czech Republic",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/cz.png"
}, {
	"country_code": 389,
	"short_name": "MK",
	"name": "Macedonia",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/mk.png"
}, {
	"country_code": 387,
	"short_name": "BA",
	"name": "Bosnia & Herzegovina",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/ba.png"
}, {
	"country_code": 386,
	"short_name": "SI",
	"name": "Slovenia",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/si.png"
}, {
	"country_code": 385,
	"short_name": "HR",
	"name": "Croatia",
	"format": "",
	"url": "https://flagcdn.com/24x18/hr.png"
}, {
	"country_code": 383,
	"short_name": "XK",
	"name": "Kosovo",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/xk.png"
}, {
	"country_code": 382,
	"short_name": "ME",
	"name": "Montenegro",
	"format": "",
	"url": "https://flagcdn.com/24x18/me.png"
}, {
	"country_code": 381,
	"short_name": "RS",
	"name": "Serbia",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/rs.png"
}, {
	"country_code": 380,
	"short_name": "UA",
	"name": "Ukraine",
	"format": "XX XXX XX XX",
	"url": "https://flagcdn.com/24x18/ua.png"
}, {
	"country_code": 378,
	"short_name": "SM",
	"name": "San Marino",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/sm.png"
}, {
	"country_code": 377,
	"short_name": "MC",
	"name": "Monaco",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/mc.png"
}, {
	"country_code": 376,
	"short_name": "AD",
	"name": "Andorra",
	"format": "XX XX XX",
	"url": "https://flagcdn.com/24x18/ad.png"
}, {
	"country_code": 375,
	"short_name": "BY",
	"name": "Belarus",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/by.png"
}, {
	"country_code": 374,
	"short_name": "AM",
	"name": "Armenia",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/am.png"
}, {
	"country_code": 373,
	"short_name": "MD",
	"name": "Moldova",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/md.png"
}, {
	"country_code": 372,
	"short_name": "EE",
	"name": "Estonia",
	"format": "",
	"url": "https://flagcdn.com/24x18/ee.png"
}, {
	"country_code": 371,
	"short_name": "LV",
	"name": "Latvia",
	"format": "XXX XXXXX",
	"url": "https://flagcdn.com/24x18/lv.png"
}, {
	"country_code": 370,
	"short_name": "LT",
	"name": "Lithuania",
	"format": "XXX XXXXX",
	"url": "https://flagcdn.com/24x18/lt.png"
}, {
	"country_code": 359,
	"short_name": "BG",
	"name": "Bulgaria",
	"format": "",
	"url": "https://flagcdn.com/24x18/bg.png"
}, {
	"country_code": 358,
	"short_name": "FI",
	"name": "Finland",
	"format": "",
	"url": "https://flagcdn.com/24x18/fi.png"
}, {
	"country_code": 357,
	"short_name": "CY",
	"name": "Cyprus",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/cy.png"
}, {
	"country_code": 356,
	"short_name": "MT",
	"name": "Malta",
	"format": "XX XX XX XX",
	"url": "https://flagcdn.com/24x18/mt.png"
}, {
	"country_code": 355,
	"short_name": "AL",
	"name": "Albania",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/al.png"
}, {
	"country_code": 354,
	"short_name": "IS",
	"name": "Iceland",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/is.png"
}, {
	"country_code": 353,
	"short_name": "IE",
	"name": "Ireland",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ie.png"
}, {
	"country_code": 352,
	"short_name": "LU",
	"name": "Luxembourg",
	"format": "",
	"url": "https://flagcdn.com/24x18/lu.png"
}, {
	"country_code": 351,
	"short_name": "PT",
	"name": "Portugal",
	"format": "X XXXX XXXX",
	"url": "https://flagcdn.com/24x18/pt.png"
}, {
	"country_code": 350,
	"short_name": "GI",
	"name": "Gibraltar",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/gi.png"
}, {
	"country_code": 299,
	"short_name": "GL",
	"name": "Greenland",
	"format": "XXX XXX",
	"url": "https://flagcdn.com/24x18/gl.png"
}, {
	"country_code": 298,
	"short_name": "FO",
	"name": "Faroe Islands",
	"format": "XXX XXX",
	"url": "https://flagcdn.com/24x18/fo.png"
}, {
	"country_code": 297,
	"short_name": "AW",
	"name": "Aruba",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/aw.png"
}, {
	"country_code": 291,
	"short_name": "ER",
	"name": "Eritrea",
	"format": "X XXX XXX",
	"url": "https://flagcdn.com/24x18/er.png"
}, {
	"country_code": 290,
	"short_name": "SH",
	"name": "Saint Helena",
	"format": "XX XXX",
	"url": "https://flagcdn.com/24x18/sh.png"
}, {
	"country_code": 269,
	"short_name": "KM",
	"name": "Comoros",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/km.png"
}, {
	"country_code": 268,
	"short_name": "SZ",
	"name": "Swaziland",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/sz.png"
}, {
	"country_code": 267,
	"short_name": "BW",
	"name": "Botswana",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/bw.png"
}, {
	"country_code": 266,
	"short_name": "LS",
	"name": "Lesotho",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/ls.png"
}, {
	"country_code": 265,
	"short_name": "MW",
	"name": "Malawi",
	"format": "77 XXX XXXX",
	"url": "https://flagcdn.com/24x18/mw.png"
}, {
	"country_code": 264,
	"short_name": "NA",
	"name": "Namibia",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/na.png"
}, {
	"country_code": 263,
	"short_name": "ZW",
	"name": "Zimbabwe",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/zw.png"
}, {
	"country_code": 262,
	"short_name": "RE",
	"name": "Réunion",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/re.png"
}, {
	"country_code": 261,
	"short_name": "MG",
	"name": "Madagascar",
	"format": "XX XX XXX XX",
	"url": "https://flagcdn.com/24x18/mg.png"
}, {
	"country_code": 260,
	"short_name": "ZM",
	"name": "Zambia",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/zm.png"
}, {
	"country_code": 258,
	"short_name": "MZ",
	"name": "Mozambique",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/mz.png"
}, {
	"country_code": 257,
	"short_name": "BI",
	"name": "Burundi",
	"format": "XX XX XXXX",
	"url": "https://flagcdn.com/24x18/bi.png"
}, {
	"country_code": 256,
	"short_name": "UG",
	"name": "Uganda",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ug.png"
}, {
	"country_code": 255,
	"short_name": "TZ",
	"name": "Tanzania",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/tz.png"
}, {
	"country_code": 254,
	"short_name": "KE",
	"name": "Kenya",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/ke.png"
}, {
	"country_code": 253,
	"short_name": "DJ",
	"name": "Djibouti",
	"format": "XX XX XX XX",
	"url": "https://flagcdn.com/24x18/dj.png"
}, {
	"country_code": 252,
	"short_name": "SO",
	"name": "Somalia",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/so.png"
}, {
	"country_code": 251,
	"short_name": "ET",
	"name": "Ethiopia",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/et.png"
}, {
	"country_code": 250,
	"short_name": "RW",
	"name": "Rwanda",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/rw.png"
}, {
	"country_code": 249,
	"short_name": "SD",
	"name": "Sudan",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/sd.png"
}, {
	"country_code": 248,
	"short_name": "SC",
	"name": "Seychelles",
	"format": "X XX XX XX",
	"url": "https://flagcdn.com/24x18/sc.png"
}, {
	"country_code": 247,
	"short_name": "SH",
	"name": "Saint Helena",
	"format": "XXXX",
	"url": "https://flagcdn.com/24x18/sh.png"
}, {
	"country_code": 246,
	"short_name": "IO",
	"name": "Diego Garcia",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/io.png"
}, {
	"country_code": 245,
	"short_name": "GW",
	"name": "Guinea-Bissau",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/gw.png"
}, {
	"country_code": 244,
	"short_name": "AO",
	"name": "Angola",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/ao.png"
}, {
	"country_code": 243,
	"short_name": "CD",
	"name": "Congo (Dem. Rep.)",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/cd.png"
}, {
	"country_code": 242,
	"short_name": "CG",
	"name": "Congo (Rep.)",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/cg.png"
}, {
	"country_code": 241,
	"short_name": "GA",
	"name": "Gabon",
	"format": "X XX XX XX",
	"url": "https://flagcdn.com/24x18/ga.png"
}, {
	"country_code": 240,
	"short_name": "GQ",
	"name": "Equatorial Guinea",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/gq.png"
}, {
	"country_code": 239,
	"short_name": "ST",
	"name": "São Tomé & Príncipe",
	"format": "XX XXXXX",
	"url": "https://flagcdn.com/24x18/st.png"
}, {
	"country_code": 238,
	"short_name": "CV",
	"name": "Cape Verde",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/cv.png"
}, {
	"country_code": 237,
	"short_name": "CM",
	"name": "Cameroon",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/cm.png"
}, {
	"country_code": 236,
	"short_name": "CF",
	"name": "Central African Rep.",
	"format": "XX XX XX XX",
	"url": "https://flagcdn.com/24x18/cf.png"
}, {
	"country_code": 235,
	"short_name": "TD",
	"name": "Chad",
	"format": "XX XX XX XX",
	"url": "https://flagcdn.com/24x18/td.png"
}, {
	"country_code": 234,
	"short_name": "NG",
	"name": "Nigeria",
	"format": "",
	"url": "https://flagcdn.com/24x18/ng.png"
}, {
	"country_code": 233,
	"short_name": "GH",
	"name": "Ghana",
	"format": "",
	"url": "https://flagcdn.com/24x18/gh.png"
}, {
	"country_code": 232,
	"short_name": "SL",
	"name": "Sierra Leone",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/sl.png"
}, {
	"country_code": 231,
	"short_name": "LR",
	"name": "Liberia",
	"format": "",
	"url": "https://flagcdn.com/24x18/lr.png"
}, {
	"country_code": 230,
	"short_name": "MU",
	"name": "Mauritius",
	"format": "",
	"url": "https://flagcdn.com/24x18/mu.png"
}, {
	"country_code": 229,
	"short_name": "BJ",
	"name": "Benin",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/bj.png"
}, {
	"country_code": 228,
	"short_name": "TG",
	"name": "Togo",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/tg.png"
}, {
	"country_code": 227,
	"short_name": "NE",
	"name": "Niger",
	"format": "XX XX XX XX",
	"url": "https://flagcdn.com/24x18/ne.png"
}, {
	"country_code": 226,
	"short_name": "BF",
	"name": "Burkina Faso",
	"format": "XX XX XX XX",
	"url": "https://flagcdn.com/24x18/bf.png"
}, {
	"country_code": 225,
	"short_name": "CI",
	"name": "Côte d`Ivoire",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/ci.png"
}, {
	"country_code": 224,
	"short_name": "GN",
	"name": "Guinea",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/gn.png"
}, {
	"country_code": 223,
	"short_name": "ML",
	"name": "Mali",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/ml.png"
}, {
	"country_code": 222,
	"short_name": "MR",
	"name": "Mauritania",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/mr.png"
}, {
	"country_code": 221,
	"short_name": "SN",
	"name": "Senegal",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/sn.png"
}, {
	"country_code": 220,
	"short_name": "GM",
	"name": "Gambia",
	"format": "XXX XXXX",
	"url": "https://flagcdn.com/24x18/gm.png"
}, {
	"country_code": 218,
	"short_name": "LY",
	"name": "Libya",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ly.png"
}, {
	"country_code": 216,
	"short_name": "TN",
	"name": "Tunisia",
	"format": "XX XXX XXX",
	"url": "https://flagcdn.com/24x18/tn.png"
}, {
	"country_code": 213,
	"short_name": "DZ",
	"name": "Algeria",
	"format": "XXX XX XX XX",
	"url": "https://flagcdn.com/24x18/dz.png"
}, {
	"country_code": 212,
	"short_name": "MA",
	"name": "Morocco",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ma.png"
}, {
	"country_code": 211,
	"short_name": "SS",
	"name": "South Sudan",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ss.png"
}, {
	"country_code": 98,
	"short_name": "IR",
	"name": "Iran",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ir.png"
}, {
	"country_code": 95,
	"short_name": "MM",
	"name": "Myanmar",
	"format": "",
	"url": "https://flagcdn.com/24x18/mm.png"
}, {
	"country_code": 94,
	"short_name": "LK",
	"name": "Sri Lanka",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/lk.png"
}, {
	"country_code": 93,
	"short_name": "AF",
	"name": "Afghanistan",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/af.png"
}, {
	"country_code": 92,
	"short_name": "PK",
	"name": "Pakistan",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/pk.png"
}, {
	"country_code": 91,
	"short_name": "IN",
	"name": "India",
	"format": "XXXXX XXXXX",
	"url": "https://flagcdn.com/24x18/in.png"
}, {
	"country_code": 90,
	"short_name": "TR",
	"name": "Turkey",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/tr.png"
}, {
	"country_code": 86,
	"short_name": "CN",
	"name": "China",
	"format": "XXX XXXX XXXX",
	"url": "https://flagcdn.com/24x18/cn.png"
}, {
	"country_code": 84,
	"short_name": "VN",
	"name": "Vietnam",
	"format": "",
	"url": "https://flagcdn.com/24x18/vn.png"
}, {
	"country_code": 82,
	"short_name": "KR",
	"name": "South Korea",
	"format": "",
	"url": "https://flagcdn.com/24x18/kr.png"
}, {
	"country_code": 81,
	"short_name": "JP",
	"name": "Japan",
	"format": "XX XXXX XXXX",
	"url": "https://flagcdn.com/24x18/jp.png"
}, {
	"country_code": 66,
	"short_name": "TH",
	"name": "Thailand",
	"format": "X XXXX XXXX",
	"url": "https://flagcdn.com/24x18/th.png"
}, {
	"country_code": 65,
	"short_name": "SG",
	"name": "Singapore",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/sg.png"
}, {
	"country_code": 64,
	"short_name": "NZ",
	"name": "New Zealand",
	"format": "",
	"url": "https://flagcdn.com/24x18/nz.png"
}, {
	"country_code": 63,
	"short_name": "PH",
	"name": "Philippines",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ph.png"
}, {
	"country_code": 62,
	"short_name": "ID",
	"name": "Indonesia",
	"format": "",
	"url": "https://flagcdn.com/24x18/id.png"
}, {
	"country_code": 61,
	"short_name": "AU",
	"name": "Australia",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/au.png"
}, {
	"country_code": 60,
	"short_name": "MY",
	"name": "Malaysia",
	"format": "",
	"url": "https://flagcdn.com/24x18/my.png"
}, {
	"country_code": 58,
	"short_name": "VE",
	"name": "Venezuela",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ve.png"
}, {
	"country_code": 57,
	"short_name": "CO",
	"name": "Colombia",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/co.png"
}, {
	"country_code": 56,
	"short_name": "CL",
	"name": "Chile",
	"format": "X XXXX XXXX",
	"url": "https://flagcdn.com/24x18/cl.png"
}, {
	"country_code": 55,
	"short_name": "BR",
	"name": "Brazil",
	"format": "XX XXXXX XXXX",
	"url": "https://flagcdn.com/24x18/br.png"
}, {
	"country_code": 54,
	"short_name": "AR",
	"name": "Argentina",
	"format": "",
	"url": "https://flagcdn.com/24x18/ar.png"
}, {
	"country_code": 53,
	"short_name": "CU",
	"name": "Cuba",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/cu.png"
}, {
	"country_code": 52,
	"short_name": "MX",
	"name": "Mexico",
	"format": "",
	"url": "https://flagcdn.com/24x18/mx.png"
}, {
	"country_code": 51,
	"short_name": "PE",
	"name": "Peru",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/pe.png"
}, {
	"country_code": 49,
	"short_name": "DE",
	"name": "Germany",
	"format": "",
	"url": "https://flagcdn.com/24x18/de.png"
}, {
	"country_code": 48,
	"short_name": "PL",
	"name": "Poland",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/pl.png"
}, {
	"country_code": 47,
	"short_name": "NO",
	"name": "Norway",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/no.png"
}, {
	"country_code": 46,
	"short_name": "SE",
	"name": "Sweden",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/se.png"
}, {
	"country_code": 45,
	"short_name": "DK",
	"name": "Denmark",
	"format": "XXXX XXXX",
	"url": "https://flagcdn.com/24x18/dk.png"
}, {
	"country_code": 44,
	"short_name": "GB",
	"name": "United Kingdom",
	"format": "XXXX XXXXXX",
	"url": "https://flagcdn.com/24x18/gb.png"
}, {
	"country_code": 43,
	"short_name": "AT",
	"name": "Austria",
	"format": "",
	"url": "https://flagcdn.com/24x18/at.png"
},  {
	"country_code": 41,
	"short_name": "CH",
	"name": "Switzerland",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ch.png"
}, {
	"country_code": 40,
	"short_name": "RO",
	"name": "Romania",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/ro.png"
}, {
	"country_code": 39,
	"short_name": "IT",
	"name": "Italy",
	"format": "",
	"url": "https://flagcdn.com/24x18/it.png"
}, {
	"country_code": 36,
	"short_name": "HU",
	"name": "Hungary",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/hu.png"
}, {
	"country_code": 34,
	"short_name": "ES",
	"name": "Spain",
	"format": "XXX XXX XXX",
	"url": "https://flagcdn.com/24x18/es.png"
}, {
	"country_code": 33,
	"short_name": "FR",
	"name": "France",
	"format": "X XX XX XX XX",
	"url": "https://flagcdn.com/24x18/fr.png"
}, {
	"country_code": 32,
	"short_name": "BE",
	"name": "Belgium",
	"format": "XXX XX XX XX",
	"url": "https://flagcdn.com/24x18/be.png"
}, {
	"country_code": 31,
	"short_name": "NL",
	"name": "Netherlands",
	"format": "X XX XX XX XX",
	"url": "https://flagcdn.com/24x18/nl.png"
}, {
	"country_code": 30,
	"short_name": "GR",
	"name": "Greece",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/gr.png"
}, {
	"country_code": 27,
	"short_name": "ZA",
	"name": "South Africa",
	"format": "XX XXX XXXX",
	"url": "https://flagcdn.com/24x18/za.png"
}, {
	"country_code": 20,
	"short_name": "EG",
	"name": "Egypt",
	"format": "XX XXXX XXXX",
	"url": "https://flagcdn.com/24x18/eg.png"
}, {
	"country_code": 7,
	"short_name": "KZ",
	"name": "Kazakhstan",
	"format": "XXX XXX XX XX",
	"url": "https://flagcdn.com/24x18/kz.png"
}, {
	"country_code": 7,
	"short_name": "RU",
	"name": "Russian Federation",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ru.png"
}, {
	"country_code": 1,
	"short_name": "PR",
	"name": "Puerto Rico",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/pr.png"
}, {
	"country_code": 1,
	"short_name": "DO",
	"name": "Dominican Rep.",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/do.png"
}, {
	"country_code": 1,
	"short_name": "CA",
	"name": "Canada",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/ca.png"
}, {
	"country_code": 1,
	"short_name": "US",
	"name": "USA",
	"format": "XXX XXX XXXX",
	"url": "https://flagcdn.com/24x18/us.png"
}]
"""
val EmptyCountry = Country(
    countryCode = 0,
    shortName = "",
    name = "",
    format = "",
    url = ""
)
val countriesRaw = Json.decodeFromString<List<Country>>(countriesData)
val countries = countriesRaw.sortedBy { it.name }