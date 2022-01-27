
mongo多表查询


db.user.aggregate([{$lookup:{from:"orders",localField:"_id",foreignField:"uid",as:"orders"}}])



db.user.aggregate([
		{$lookup:{from:"orders",localField:"_id",foreignField:"uid",as:"orders"},
		{$unwind:"$orders"}
])

