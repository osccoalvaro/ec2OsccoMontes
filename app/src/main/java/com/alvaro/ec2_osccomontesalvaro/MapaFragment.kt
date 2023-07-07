package com.alvaro.ec2_osccomontesalvaro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alvaro.ec2_osccomontesalvaro.databinding.FragmentMapaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapaFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapaBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentMap = childFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        fragmentMap.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val parqueLeyendas = LatLng(-12.0725333,-77.0871048)
        val circuitoAgua = LatLng(-12.0703103,-77.0348573)
        val plazaLima = LatLng(-12.0460038,-77.0305458)
        map.addMarker(MarkerOptions().title("El Parque De Las Leyendas").position(parqueLeyendas))
        map.addMarker(MarkerOptions().title("Circuito Mágico del Agua").position(circuitoAgua))
        map.addMarker(MarkerOptions().title("Plaza Mayor de Lima").position(plazaLima))

        val bounds = LatLngBounds.Builder()
        bounds.include(parqueLeyendas)
        bounds.include(circuitoAgua)
        bounds.include(plazaLima)
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(),150))
    }

}