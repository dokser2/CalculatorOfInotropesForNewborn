package ua.dokser.calculatorforinotropesfornewborn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import ua.dokser.calculatorforinotropesfornewborn.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {


    var weight:Double? = 0.0
    var dose_of_inotrop: Double? = 0.0
    var infusion_rate:Double? = 0.0
    var concentration_of_inotrop: Double? = 0.0
    var volume_of_syringe = 0
    var  result:Double = 0.0

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    fun onClickResult(view: View){
        binding.apply {
            weight = etWeight.text.toString().toDouble()
            dose_of_inotrop = etDoseOfInotrope.text.toString().toDouble()
            infusion_rate = etInfusionRate.text.toString().toDouble()
            concentration_of_inotrop = etConcentratiaOfInotrope.text.toString().toDouble()
            if (cb50.isChecked&&!cb20.isChecked) volume_of_syringe =50
            else if (cb20.isChecked&&!cb50.isChecked) volume_of_syringe =20

            result = calculateOfInotropes(weight!!,dose_of_inotrop!!,infusion_rate!!,concentration_of_inotrop!!,volume_of_syringe)
            tvResult.text = "Вам потрібно набрати в шприц: ${result.toString()} мл   $concentration_of_inotrop %  розчину"
        }
    }
}


private fun calculateOfInotropes(
    weight:Double,
    dose_of_inotrop: Double,
    infusion_rate: Double,
    concentration_of_inotrop: Double,
    volume_of_syringe: Int
) : Double {
    var result = (weight*dose_of_inotrop*6*volume_of_syringe)/(infusion_rate*concentration_of_inotrop*1000)
    Log.d("MyLog","Result= $result")
    result = (result*100).roundToInt()/100.0
    return result
    }



