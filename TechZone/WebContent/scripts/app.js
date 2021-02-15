
/* ==============================================
Google Map
=============================================== */

	function initialize() {
	var myLatlng = new google.maps.LatLng(40.710079, -74.003027); // Change your location Latitude and Longitude 
	var mapOptions = {
	zoom: 15,
	center: myLatlng
	}
	var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

// style for Google Map
	var styles = [
    {
        featureType: "water",
        elementType: "geometry.fill",
        stylers: [
            {
                color: "#bdc3c7"
            }
        ]
    },
    {
        featureType: "transit",
        stylers: [
            {
                color: "#333"
            },
            {
                visibility: "off"
            }
        ]
    },
    {
        featureType: "road.highway",
        elementType: "geometry.stroke",
        stylers: [
            {
                visibility: "on"
            },
            {
                color: "#333"
            }
        ]
    },
    {
        featureType: "road.highway",
        elementType: "geometry.fill",
        stylers: [
            {
                color: "#ffffff"
            }
        ]
    },
    {
        featureType: "road.local",
        elementType: "geometry.fill",
        stylers: [
            {
                visibility: "on"
            },
            {
                color: "#ffffff"
            },
            {
               weight: 1.8
            }
        ]
    },
    {
        featureType: "road.local",
        elementType: "geometry.stroke",
        stylers: [
            {
                color: "black"
            }
        ]
    },
    {
        featureType: "poi",
        elementType: "geometry.fill",
        stylers: [
            {
                visibility: "on"
            },
            {
                color: "#ebebeb"
            }
        ]
    },
    {
        featureType: "administrative",
        elementType: "geometry",
        stylers: [
            {
                color: "black"
            }
        ]
    },
    {
        featureType: "road.arterial",
        elementType: "geometry.fill",
        stylers: [
            {
                color: "#ffffff"
            }
        ]
    },
    {
        featureType: "road.arterial",
        elementType: "geometry.fill",
        stylers: [
            {
                color: "#ffffff"
            }
        ]
    },
    {
        featureType: "landscape",
        elementType: "geometry.fill",
        stylers: [
            {
                visibility: "on"
            },
            {
                color: "#333"
            }
        ]
    },
    {
        featureType: "road",
        elementType: "labels.text.fill",
        stylers: [
            {
                color: "#505050"
            }
        ]
    },
    {
        featureType: "administrative",
        elementType: "labels.text.fill",
        stylers: [
            {
                visibility: "on"
            },
            {
                color: "#C0C0C0"
            }
        ]
    },
    {
        featureType: "poi",
        elementType: "labels.icon",
        stylers: [
            {
                visibility: "off"
            }
        ]
    },
    {
        featureType: "poi",
        elementType: "labels",
        stylers: [
            {
                visibility: "off"
            }
        ]
    },
    {
        featureType: "road.arterial",
        elementType: "geometry.stroke",
        stylers: [
            {
                color: "red"
            }
        ]
    },
    {
        featureType: "road",
        elementType: "labels.icon",
        stylers: [
            {
                visibility: "off"
            }
        ]
    },
    {},
    {
        featureType: "poi",
        elementType: "geometry.fill",
        stylers: [
            {
                color: "#dadada"
            }
        ]
    }
]
	map.setOptions({styles: styles});

// Google Map Maker 
	var marker = new google.maps.Marker({
	position: myLatlng,
	map: map,
	});
	}

	google.maps.event.addDomListener(window, 'load', initialize);