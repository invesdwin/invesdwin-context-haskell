println("getFloat")
if isdefined(Main, :getFloat) && !isnothing(getFloat)
	error("getFloat already defined!")
end
getFloat = callback("getFloat")
println(typeof(getFloat))
println(getFloat)
if typeof(getFloat) != Float32
	error("getFloat not Float32!")
end
callback("setFloat",getFloat)

println("getFloatVector")
if isdefined(Main, :getFloatVector) && !isnothing(getFloatVector)
	error("getFloatVector already defined!")
end
getFloatVector = callback("getFloatVector")
println(eltype(getFloatVector))
println(getFloatVector)
if eltype(getFloatVector) != Float32
	error("getFloatVector not Float32!")
end
callback("setFloatVector",getFloatVector)

println("getFloatVectorAsList")
if isdefined(Main, :getFloatVectorAsList) && !isnothing(getFloatVectorAsList)
	error("getFloatVectorAsList already defined!")
end
getFloatVectorAsList = callback("getFloatVectorAsList")
println(eltype(getFloatVectorAsList))
println(getFloatVectorAsList)
if eltype(getFloatVectorAsList) != Float32
	error("getFloatVectorAsList not Float32!")
end
callback("setFloatVectorAsList",getFloatVectorAsList)

println("getFloatMatrix")
if isdefined(Main, :getFloatMatrix) && !isnothing(getFloatMatrix)
	error("getFloatMatrix already defined!")
end
getFloatMatrix = callback("getFloatMatrix")
println(eltype(getFloatMatrix))
println(getFloatMatrix)
if eltype(getFloatMatrix) != Float32
	error("getFloatMatrix not Float32!")
end
callback("setFloatMatrix",getFloatMatrix)

println("getFloatMatrixAsList")
if isdefined(Main, :getFloatMatrixAsList) && !isnothing(getFloatMatrixAsList)
	error("getFloatMatrixAsList already defined!")
end
getFloatMatrixAsList = callback("getFloatMatrixAsList")
println(eltype(getFloatMatrixAsList))
println(getFloatMatrixAsList)
if eltype(getFloatMatrixAsList) != Float32
	error("getFloatMatrixAsList not Float32!")
end
callback("setFloatMatrixAsList",getFloatMatrixAsList)
