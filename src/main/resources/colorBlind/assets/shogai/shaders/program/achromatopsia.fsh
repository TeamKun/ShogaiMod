uniform sampler2D DiffuseSampler;
varying vec2 texCoord;

const mat4 mAchromatopsia = mat4(0.299, 0.587 ,  0.114,  0.0 ,
								 0.299, 0.587 ,  0.114,  0.0 ,
								 0.299, 0.587 ,  0.114,  0.0 ,
                                 0.0  ,  0.0  ,  0.0  ,  1.0 );

void main()
{
	vec4 color = texture2D(DiffuseSampler, texCoord);
	
	gl_FragColor = color*mAchromatopsia;
}